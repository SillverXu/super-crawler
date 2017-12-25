package com.bmsoft.xu.controller;

import com.bmsoft.xu.bean.CrawlerBean;
import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.bean.TaskBean;
import com.bmsoft.xu.service.ConnectionService;
import com.bmsoft.xu.service.JsoupService;
import com.bmsoft.xu.service.impl.ConnectionServiceImpl;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.util.*;

import static com.bmsoft.xu.utils.Var.*;

public class TaskController {
    private static Logger logger = Logger.getLogger(TaskController.class);

    private ConnectionService connectionService;
    private JsoupService jsoupService;

    /**
     * get TaskBean and use it to get links(Map<K,V>)
     *
     * @return Map<String       ,       CrawlerBean>
     */
    public Map<String, CrawlerBean> analyTask(TaskBean taskBean) {
        Map<String, CrawlerBean> map = new HashMap<String, CrawlerBean>();
        String url_start = taskBean.getUrl_start();
        String url_end = taskBean.getUrl_end();
        int pages = taskBean.getPages();
        int requestType = taskBean.getRequesttype();
        List<BasicNameValuePair> requestParams = taskBean.getRequestParams()==null? null : parseRequestParams(taskBean.getRequestParams());
        Map<String, String> requestHeaders = taskBean.getRequestHeaders();
        CrawlerBean crawlerBean = taskBean.getCrawlerBean();
        String pinjieStr = taskBean.getPinjieStr();
        List<RuleBean> ruleList = taskBean.getRuleList();


        return map;
    }

    public boolean checkPages(int pages) {
        if (pages < 0) {
            return false;
        }
        if (pages > 1) {
            return true;
        }
        pages = 1;
        return true;
    }

    public String checkURL(String url) {
        if (!url.startsWith("http://")) {
            logger.info("URL设置错误，导致无法开始爬虫任务！");
            return null;
        }
        return url;
    }

    /**
     * 把bean中的map 参数转换成http请求的参数组
     *
     * @param requestParams map
     */
    public List<BasicNameValuePair> parseRequestParams(Map<String, String> requestParams) {
        List<BasicNameValuePair> params = new ArrayList<>();
        requestParams.forEach((k, v) -> params.add(new BasicNameValuePair(k, v)));
        return params;
    }

    /**
     * Single page crawler
     *
     * @param url            absolute url
     * @param requestType
     * @param requestHeaders
     * @param ruleList       method to crawl urls(only Jsoup now)
     */
    private Set<String> getURLsOnePage(String url, int requestType, Map<String, String> requestHeaders, List<BasicNameValuePair> requestParams, List<RuleBean> ruleList) {
        connectionService = new ConnectionServiceImpl();
        Set<String> set = new HashSet<String>();
        String html;
        switch (requestType) {
            case HTTPGET:
                if (requestHeaders == null) {
                    requestHeaders = httpheader;
                }
                html = connectionService.HttpResquestGet(url, requestHeaders);
                break;
            case HTTPPOST:
                if (requestHeaders == null) {
                    requestHeaders = httpheader;
                }
                html = connectionService.HttpResquestPost(url, requestParams, requestHeaders);
                break;
            case PHANTOMJSREQ:
                html = connectionService.PhantomjsRequest(url);
                break;
            default:
                logger.info("%%%%%%%%%%%%%%requestType set error%%%%%%%%%%%%%%%%%%%%%");
                return null;
        }
        return set;
    }

    /**
     * Multi-pages crawler
     */
    private Set<String> getURLsByPages(String url_start, String url_end, int requestTypei, int pages,
                                       Map<String, String> requestParams, List<RuleBean> ruleList) {
        Set<String> set = new HashSet<String>();

        return set;
    }
}
