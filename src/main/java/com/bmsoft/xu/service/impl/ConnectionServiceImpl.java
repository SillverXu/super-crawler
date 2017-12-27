package com.bmsoft.xu.service.impl;

import com.bmsoft.xu.service.ConnectionService;
import com.bmsoft.xu.utils.PhantomjsUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ConnectionServiceImpl implements ConnectionService {
    private static Logger logger = Logger.getLogger(ConnectionServiceImpl.class);
    private CloseableHttpClient httpClient;
    private CloseableHttpResponse httpresponse;

    /**
     * Http Get 请求
     *
     * @param url        请求地址
     * @param httpheader header信息
     * @return html 网页html
     */
    public String HttpResquestGet(String url, Map<String, String> httpheader) {
        return HttpResquestGet(url, httpheader, null);
    }

    public String HttpResquestGet(String url, Map<String, String> headers, int[] timout) {     //todo 传参预留延迟设置，暂时用默认值
        if (url.length() < 11 || !url.startsWith("http")) {
            return null;
        }
        String html = null;
        httpClient = HttpClients.createDefault();
        try {
            //延迟为默认设置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(90000).setConnectionRequestTimeout(10000)
                    .setSocketTimeout(90000).build();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            if (headers != null) {
                headers.forEach((k, v) -> httpGet.setHeader(k, v));
            }
            httpresponse = httpClient.execute(httpGet);
            if (checkResponse(httpresponse, url)) {
                html = responseEntityToString(httpresponse);
            }
        } catch (ClientProtocolException e) {
            logger.error("httpget client error!" + e.getMessage(), e);
        } catch (IOException e) {
            logger.error("httpget client ioerror!" + e.getMessage(), e);
        } finally {
            closeClient(httpresponse, httpClient);
        }
        return html;
    }

    /**
     * phantomjs请求，为了延迟加载页面获取html
     * todo 抽象方法完善，目前只支持税务局页面
     *
     * @url 请求地址
     */
    public String PhantomjsRequest(String url) {
        WebDriver webDriver = PhantomjsUtil.getPhantomJs();
        String html = null;
        try {
            webDriver.get(url);
            WebDriverWait wait = new WebDriverWait(webDriver, 90, 2 * 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"legal\"]/tbody/tr[50]/td[1]/a")));
            html = webDriver.getPageSource();
        } finally {
            if (webDriver != null)
                webDriver.quit();
        }
        return html;
    }

    /**
     * Http Post 请求
     *
     * @param url        链接地址
     * @param httpparams post请求的参数
     * @param httpheader post请求的header
     */
    public String HttpResquestPost(String url, List<BasicNameValuePair> httpparams, Map<String, String> httpheader) {
        return HttpResquestPost(url, httpparams, httpheader, null);
    }

    public String HttpResquestPost(String url, List<BasicNameValuePair> httpparams, Map<String, String> httpheader, int[] timeout) {                               //todo 总结方法抽象出翻页方法
        if (url.length() < 11 || !url.startsWith("http")) {
            return null;
        }
        String html = null;
        httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(90000).setConnectTimeout(10000)
                .setSocketTimeout(90000).build();
        httpresponse = null;
        try {
            if (httpparams != null)
                post.setEntity(new UrlEncodedFormEntity(httpparams));
            post.setConfig(config);
            if (httpheader != null) {
                httpheader.forEach((k, v) -> post.setHeader(k, v));
            }
            httpresponse = httpClient.execute(post);
            if (checkResponse(httpresponse, url)) {
                html = responseEntityToString(httpresponse);
            }
        } catch (ClientProtocolException e) {
            logger.error("httppost client error!" + e.getMessage(), e);
        } catch (IOException e) {
            logger.error("httppost client ioerror!" + e.getMessage(), e);
        } finally {
            closeClient(httpresponse, httpClient);
        }
        return html;
    }

    /**
     * 获取response中的html页面，设置为UTF-8的字符串
     *
     * @param response
     */
    private String responseEntityToString(CloseableHttpResponse response) {
        String html = null;
        HttpEntity entity = response.getEntity();
        try {
            html = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error("response error" + e.getMessage(), e);
        }
        return html;
    }

    /**
     * 判断httpresponse是否遇到404等错误
     * todo 加入更多的状态码
     *
     * @param response
     */
    private boolean checkResponse(CloseableHttpResponse response, String url) {
        if ("HTTP/1.1 404 Not Found".equals(response.getStatusLine().toString())) {
            logger.info(url + "404 Not Found");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 关闭httpresponse和httpclient连接
     *
     * @param response
     * @param client
     */
    private void closeClient(CloseableHttpResponse response, CloseableHttpClient client) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}