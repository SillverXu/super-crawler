package com.bmsoft.xu.utils;

import java.util.HashMap;
import java.util.Map;

public class Var {
    public final static Map<String, String> httpheader = new HashMap<String, String>() ;
    static{
        httpheader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpheader.put("Accept-Encoding", "gzip,deflate");
        httpheader.put("Accept-Language", "zh-CN,zh;q=0.9");
        httpheader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
    };
    public static final int HTTPGET = 0;
    public static final int HTTPPOST = 1;
    public static final int PHANTOMJSREQ = 2;
}
