package com.bmsoft.xu.service.impl;
import com.bmsoft.xu.bean.RuleBean;
import com.bmsoft.xu.service.JsoupService;
import com.bmsoft.xu.utils.TextUtil;
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

   /*
   * 一次请求从HTML》》Elements
   * */
    public Elements extract(String page, RuleBean ruleBean) {
        String resultTagName = ruleBean.getResultTagName();
        int type = ruleBean.getType();
        Document doc = null;
        try {
            doc = Jsoup.parse(page);
        } catch (Exception e) {
            logger.error("JSoup fail to change html to Doc ",e);
        }
        Elements results = new Elements();
        switch (type) {
            case RuleBean.CLASS:
                results = doc.getElementsByClass(resultTagName);
                break;
            case RuleBean.ID:
                Element result = doc.getElementById(resultTagName);
                results.add(result);
                break;
            case RuleBean.SELECTION:
                results = doc.select(resultTagName);
                break;
            case RuleBean.TAG:
                results = doc.getElementsByTag(resultTagName);
                break;
            default:

                if (TextUtil.isEmpty(resultTagName)) {
                    results = doc.getElementsByTag("body");
                }
        }
        //logger.info("---------results的长度:"+results.size());

        return results;
    }


}
