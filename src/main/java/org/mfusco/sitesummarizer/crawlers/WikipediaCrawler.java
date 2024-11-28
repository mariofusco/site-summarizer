package org.mfusco.sitesummarizer.crawlers;

import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WikipediaCrawler extends SiteCrawler {

    public static final WikipediaCrawler INSTANCE = new WikipediaCrawler();

    private WikipediaCrawler() { }

    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";

    @Override
    protected String topicToUrl(String topic) {
        return WIKIPEDIA_URL + topic;
    }

    protected Element findMainElement(Document doc) {
        return doc.body().getElementsByClass("mw-body-content").first();
    }
}
