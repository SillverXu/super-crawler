package com.bmsoft.xu.service;


import com.bmsoft.xu.bean.RuleBean;
import org.jsoup.select.Elements;

/**
 * Created by 81954 on 2017/11/19.
 */
public interface JsoupService {
    /**
     * 处理一次Jsoup操作
     *
     * @param page     html页面
     * @param ruleBean 使用规则
     */
    Elements extract(String page, RuleBean ruleBean);
    /**
     * 处理一次Jsoup操作
     *
     * @param elements  页面元素
     * @param ruleBean 使用规则
     */
    Elements extract(Elements elements,RuleBean ruleBean);
}
