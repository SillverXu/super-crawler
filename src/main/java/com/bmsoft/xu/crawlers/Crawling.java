package com.bmsoft.xu.crawlers;

import com.bmsoft.xu.bean.RuleBean;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Crawling {
    public Set<String> getURLs(String url_start, String url_end, int pages, int requestType,
                               Map<String,String> requestHeaders, List<BasicNameValuePair> requestParams, List<RuleBean>ruleList);
}
