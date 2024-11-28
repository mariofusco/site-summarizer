package org.mfusco.sitesummarizer;

import org.mfusco.sitesummarizer.crawlers.BlogCrawler;
import org.mfusco.sitesummarizer.crawlers.SiteCrawler;
import org.mfusco.sitesummarizer.crawlers.WikipediaCrawler;

public enum SiteType {
    WIKIPEDIA, BLOG;

    public SiteCrawler getCrawler() {
        return switch (this) {
            case WIKIPEDIA -> WikipediaCrawler.INSTANCE;
            case BLOG -> BlogCrawler.INSTANCE;
        };
    }

    public static SiteType determineType(String typeName) {
        if (typeName.equalsIgnoreCase("wiki")) {
            return WIKIPEDIA;
        }
        if (typeName.equalsIgnoreCase("blog")) {
            return BLOG;
        }
        throw new IllegalArgumentException("Unknown site type: " + typeName);
    }
}
