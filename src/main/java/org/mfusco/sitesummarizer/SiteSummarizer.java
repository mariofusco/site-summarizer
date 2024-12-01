package org.mfusco.sitesummarizer;

import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mfusco.sitesummarizer.crawlers.SiteCrawler;
import org.slf4j.Logger;

import java.util.function.Supplier;

@ApplicationScoped
class SiteSummarizer {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SiteSummarizer.class);

    @Inject
    private SummarizerAiService summarizerAiService;

    public Multi<String> summarize(SiteType siteType, String topic) {
        String html = timedExecutor("Site crawl", () -> SiteCrawler.crawl(siteType, topic));
        String content = timedExecutor("Text extraction", () -> TextExtractor.extractText(html, 20_000));
        LOGGER.debug("Extracted content: " + content);
        return summarizeContent(content);
    }

    private Multi<String> summarizeContent(String content) {
        while (true) {
            try {
                LOGGER.info("Summarizing content " + content.length() + " characters long");
                return summarizerAiService.summarize(content);
            } catch (Exception e) {
                if (content.length() > 1_000) {
                    content = content.substring(0, content.length() - 1_000);
                } else {
                   // just keep it as it s too short to summarize
                   return Multi.createFrom().item(content);

                }
            }
        }
    }

    private <T> T timedExecutor(String name, Supplier<T> supplier) {
        long start = System.currentTimeMillis();
        T result = supplier.get();
        long end = System.currentTimeMillis();
        LOGGER.info(name + " took " + (end - start) + " ms");
        return result;
    }
}
