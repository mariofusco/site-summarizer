package org.mfusco.sitesummarizer;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;

@QuarkusMain
public class SiteSummarizerMain implements QuarkusApplication {

    @Inject
    private SiteSummarizer siteSummarizer;

    @Override
    public int run(String... args) {
        if (args.length > 0) {
            long startTime = System.currentTimeMillis();
            Multi<String> sum = args.length == 1 ?
                    siteSummarizer.summarize(SiteType.BLOG, args[0]) :
                    siteSummarizer.summarize(SiteType.determineType(args[0]), args[1]);
            sum.subscribe().with(
                    System.out::print,
                    () -> System.out.println("\n---\nSite summarization done in " + (System.currentTimeMillis() - startTime) + " ms"));
        } else {
            Quarkus.waitForExit();
        }
        return 0;
    }
}

