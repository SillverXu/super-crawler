package com.bmsoft.xu.controller;

import com.bmsoft.xu.bean.CrawlerBean;
import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.bean.TaskBean;
import com.bmsoft.xu.crawlers.CommonCrawler;
import com.bmsoft.xu.crawlers.CrawlerFactory;
import com.bmsoft.xu.service.ConnectionService;
import com.bmsoft.xu.service.JsoupService;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.util.*;


public class TaskController {
    private static Logger logger = Logger.getLogger(TaskController.class);

    /**
     * get TaskBean and use it to get links(Map<K,V>)
     *
     * @param taskBean
     * @return Map
     */
    public Map<String, CrawlerBean> analyTask(TaskBean taskBean) {
        Map<String, CrawlerBean> map = new HashMap<String, CrawlerBean>();
        String url_start = taskBean.getUrl_start();
        String url_end = taskBean.getUrl_end();
        int pages = taskBean.getPages();
        pages = checkPages(pages) ? pages : -1;
        int requestType = taskBean.getRequesttype();
        List<BasicNameValuePair> requestParams = taskBean.getRequestParams() == null ? null : parseRequestParams(taskBean.getRequestParams());
        Map<String, String> requestHeaders = taskBean.getRequestHeaders();
        boolean ispinjie = taskBean.getisPinjie();
        String pinjieStr = taskBean.getPinjieStr();
        CrawlerBean crawlerBean = taskBean.getCrawlerBean();
        if (crawlerBean == null) {
            logger.info("crawlBean is null!");
            throw new NullPointerException();
        }
        List<RuleBean> ruleList = taskBean.getRuleList();
        if (ruleList == null) {
            logger.info("ruleList is null!");
            throw new NullPointerException();
        }
        CrawlerFactory cfactory = new CrawlerFactory();
        CommonCrawler crawler = cfactory.createCrawler(url_start,url_end,pages,ispinjie,pinjieStr);
        Set<String> links = crawler.getURLs(requestType, requestHeaders, requestParams, ruleList);
        links.forEach((s) -> map.put(s,crawlerBean));
        System.out.println("抓到："+map.size()+"条");
        return map;
    }

    /**
     * 检查页码数格式
     *
     * @param pages
     */
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
}
