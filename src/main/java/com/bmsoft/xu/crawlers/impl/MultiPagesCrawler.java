package com.bmsoft.xu.crawlers.impl;

import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.crawlers.CommonCrawler;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiPagesCrawler extends CommonCrawler {
    private String url_start;
    private String url_end;
    private int pages;


    public MultiPagesCrawler(String url_start,String url_end,int pages) {
        this.url_start = url_start;
        this.url_end = url_end;
        this.pages = pages;
    }


    @Override
    public Set<String> getURLs(String url_start, String url_end, int pages, int requestType, Map<String, String> requestHeaders, List<BasicNameValuePair> requestParams, List<RuleBean> ruleList) {
        return null;
    }
}


