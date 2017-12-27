package com.bmsoft.xu.crawlers.impl;

import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.crawlers.CommonCrawler;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SinglePageCrawler extends CommonCrawler {
    private String url;

    public String getUrl() {
        return url;
    }

    public SinglePageCrawler(String url){
        this.url = url;
    }

    @Override
    public Set<String> getURLs(String url_start, String url_end, int pages, int requestType, Map<String, String> requestHeaders, List<BasicNameValuePair> requestParams, List<RuleBean> ruleList) {
        return super.getURLs(url_start,requestType,requestHeaders,requestParams,ruleList);
    }
}
