package com.bmsoft.xu.crawlers.impl;

import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.crawlers.CommonCrawler;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SinglePageCrawler extends CommonCrawler {

    public SinglePageCrawler(String url, String pinjieStr, boolean ispinjie) {
        super(url, pinjieStr, ispinjie);
    }

    public Set<String> getURLs(int requestType, Map<String, String> requestHeaders, List<BasicNameValuePair> requestParams, List<RuleBean> ruleList){
        return getURLs(super.getUrl(),requestType,requestHeaders,requestParams,ruleList);
    }
}
