package com.bmsoft.xu.crawlers.impl;

import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.crawlers.CommonCrawler;
import org.apache.http.message.BasicNameValuePair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiPagesCrawler extends CommonCrawler {
    private String url_start;
    private String url_end;
    private int pages;



    public MultiPagesCrawler(String url_start,String url_end,int pages,boolean ispinjie,String pinjieStr) {
        super(url_start,pinjieStr,ispinjie);
        this.url_start = url_start;
        this.url_end = url_end;
        this.pages = pages;
    }



    public Set<String> getURLs(int requestType,Map<String,String> requestHeaders,List<BasicNameValuePair> requestParams,List<RuleBean> ruleList) {
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= pages; i++) {
            set.addAll(getURLs(url_start+i+url_end,requestType,requestHeaders,requestParams,ruleList));
        }
        return set;
    }
}


