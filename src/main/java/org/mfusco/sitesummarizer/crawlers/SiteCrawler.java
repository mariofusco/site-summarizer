package org.mfusco.sitesummarizer.crawlers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mfusco.sitesummarizer.SiteType;

import java.io.IOException;
import java.io.UncheckedIOException;

public abstract class SiteCrawler {

    public static String crawl(SiteType siteType, String topic) {
        return siteType.getCrawler().crawl(topic);
    }

    private String crawl(String topic) {
        String url = topicToUrl(topic);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return findMainElement(doc).html();
    }

    protected Element findMainElement(Document doc) {
        return doc.body();
    }

    protected String topicToUrl(String topic) {
        return topic;
    }
}
