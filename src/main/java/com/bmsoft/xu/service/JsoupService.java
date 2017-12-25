package com.bmsoft.xu.service;


import com.bmsoft.xu.bean.RuleBean;
import org.jsoup.select.Elements;

/**
 * Created by 81954 on 2017/11/19.
 */
public interface JsoupService {
     Elements extract(String page, RuleBean ruleBean);
}
