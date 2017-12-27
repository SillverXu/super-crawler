package com.bmsoft.xu.service.impl;

import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.service.JsoupService;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by 81954 on 2017/11/19.
 */
public class JsoupServiceImpl implements JsoupService {
    private static Logger logger = Logger.getLogger(JsoupServiceImpl.class);

    @Override
    public Elements extract(String html, RuleBean ruleBean) {
        String resultTagName = ruleBean.getResultTagName();
        int type = ruleBean.getType();
        int index = ruleBean.getIndex();

        Document doc = Jsoup.parse(html);
        Elements results = new Elements();
        Element result ;
        if (index >= 0) {
            switch (type) {
                case RuleBean.CLASS:
                    result = doc.getElementsByClass(resultTagName).get(index);
                    results.add(result);
                    break;
                case RuleBean.ID:
                    result = doc.getElementById(resultTagName);
                    results.add(result);
                    break;
                case RuleBean.SELECTION:
                    result = doc.select(resultTagName).get(index);
                    results.add(result);
                    break;
                case RuleBean.TAG:
                    result = doc.getElementsByTag(resultTagName).get(index);
                    results.add(result);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case RuleBean.CLASS:
                    results = doc.getElementsByClass(resultTagName);
                    break;
                case RuleBean.ID:
                    result = doc.getElementById(resultTagName);
                    results.add(result);
                    break;
                case RuleBean.SELECTION:
                    results = doc.select(resultTagName);
                    break;
                case RuleBean.TAG:
                    results = doc.getElementsByTag(resultTagName);
                    break;
                default:
                    break;
            }
        }
        return results;
    }

    @Override
    public Elements extract(Elements elements, RuleBean ruleBean) {
        String resultTagName = ruleBean.getResultTagName();
        int type = ruleBean.getType();
        int index = ruleBean.getIndex();
        switch (type) {
            case RuleBean.CLASS:
                break;
            case RuleBean.ID:
                break;
            case RuleBean.SELECTION:
                if (index >= 0) {
                    Element element = elements.select(resultTagName).get(index);
                    elements = new Elements();
                    elements.add(element);
                } else {
                    elements = elements.select(resultTagName);
                }
                break;
            case RuleBean.TAG:
                break;
            default:
                break;
        }
        return elements;
    }
}
