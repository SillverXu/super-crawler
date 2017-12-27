package com.bmsoft.xu.crawlers;

import com.bmsoft.xu.crawlers.impl.MultiPagesCrawler;
import com.bmsoft.xu.crawlers.impl.SinglePageCrawler;

public class CrawlerFactory {
    public CommonCrawler createCrawler(String url_start, String url_end, int pages, boolean ispinjie, String pinjieStr) {
        if (url_start != null && url_end == null && pages < 0) {
            return new SinglePageCrawler(url_start,pinjieStr,ispinjie);
        } else if (url_start != null && url_end != null && pages > 0) {
            return new MultiPagesCrawler(url_start, url_end, pages,ispinjie,pinjieStr);
        }
        return null;
    }

}


