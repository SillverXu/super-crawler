package com.bmsoft.xu.bean;

import java.util.List;
import java.util.Map;

import static com.bmsoft.xu.utils.Var.HTTPGET;

/**
 * 任务请求的封装类
 */
public class TaskBean {

    private int requesttype = HTTPGET;  //默认为HTTPGET
    private Map<String, String> requestParams = null;       //请求所带参数
    private Map<String, String> requestHeaders = null;
    private String url_start = null;       //url 前部
    private String url_end = null;         //url 后部
    private int pages = -1;              //翻页操作的页数
    private CrawlerBean crawlerBean = null;    //爬取页面策略
    private List<RuleBean> ruleList = null;   //爬取首页策略
    private boolean isPinjie = false;
    private String pinjieStr = null;

    public TaskBean() {
    }

    public TaskBean(String url_start, String url_end, int requesttype, Map requestParams, int pages,
                    Map<String,String> requestHeaders,CrawlerBean crawlerBean, boolean isPinjie, String pinjieStr, List<RuleBean> ruleList) {
        this.requesttype = requesttype;
        this.requestParams = requestParams;
        this.requestHeaders = requestHeaders;
        this.url_start = url_start;
        this.url_end = url_end;
        this.pages = pages;
        this.crawlerBean = crawlerBean;
        this.isPinjie = isPinjie;
        this.pinjieStr = pinjieStr;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public int getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(int requesttype) {
        this.requesttype = requesttype;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    public String getUrl_start() {
        return url_start;
    }

    public void setUrl_start(String url_start) {
        this.url_start = url_start;
    }

    public String getUrl_end() {
        return url_end;
    }

    public void setUrl_end(String url_end) {
        this.url_end = url_end;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public CrawlerBean getCrawlerBean() {
        return crawlerBean;
    }

    public void setCrawlerBean(CrawlerBean crawlerBean) {
        this.crawlerBean = crawlerBean;
    }

    public boolean getisPinjie() {
        return isPinjie;
    }

    public void setPinjie(boolean pinjie) {
        isPinjie = pinjie;
    }

    public String getPinjieStr() {
        return pinjieStr;
    }

    public void setPinjieStr(String pinjieStr) {
        this.pinjieStr = pinjieStr;
    }

    public List<RuleBean> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<RuleBean> ruleList) {
        this.ruleList = ruleList;
    }

    public boolean isPinjie() {
        return isPinjie;
    }
}
