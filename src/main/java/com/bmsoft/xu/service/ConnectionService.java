package com.bmsoft.xu.service;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;

public interface ConnectionService {
    String HttpResquestGet(String url, Map<String, String> params, int[] timout);

    String HttpResquestGet(String url, Map<String, String> httpheader);

    String HttpResquestPost(String url, List<BasicNameValuePair> httpparams, Map<String, String> httpheader, int[] timeout);

    String HttpResquestPost(String url, List<BasicNameValuePair> httpparams, Map<String, String> httpheader);

    String PhantomjsRequest(String url);


}
