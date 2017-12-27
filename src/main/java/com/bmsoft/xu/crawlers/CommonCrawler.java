package com.bmsoft.xu.crawlers;

import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.service.ConnectionService;
import com.bmsoft.xu.service.JsoupService;
import com.bmsoft.xu.service.impl.ConnectionServiceImpl;
import com.bmsoft.xu.service.impl.JsoupServiceImpl;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.bmsoft.xu.utils.Var.*;

public abstract class CommonCrawler implements Crawling{
    private static Logger logger = Logger.getLogger(CommonCrawler.class);

    private ConnectionService connectionService;
    private JsoupService jsoupService;

    public CommonCrawler(){

    }

    /**
     * Single page crawler
     *
     * @param url            absolute url
     * @param requestType
     * @param requestHeaders
     * @param ruleList       method to crawl urls(only Jsoup now)
     */
    public Set<String> getURLs(String url, int requestType, Map<String, String> requestHeaders, List<BasicNameValuePair> requestParams, List<RuleBean> ruleList) {
        connectionService = new ConnectionServiceImpl();
        Set<String> set = new HashSet<String>();
        String html = null;
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
        }
        System.out.println(html);
        Elements results = getResults(html, ruleList);
        results.forEach((r) -> {
            String link = r.select("a").attr("href");
            set.add(link);
        });
        return set;
    }

    /**
     * 传入html，并用jsoup解析
     *
     * @param html     String
     * @param rulelist 解析具体方法
     */
    public Elements getResults(String html, List<RuleBean> rulelist) {
        Elements results = null;
        jsoupService = new JsoupServiceImpl();
        if (html == null) {
            logger.info("Crawl first page's html is null! ");
            return null;
        }
        results = jsoupService.extract(html, rulelist.get(0));
        if (rulelist.size() > 1) {
            for (int i = 1; i < rulelist.size(); i++) {
                results = jsoupService.extract(results, rulelist.get(i));
            }
        }
        return results;
    }

}
