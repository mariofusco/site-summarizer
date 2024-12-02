package org.mfusco.sitesummarizer;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Singleton;

@RegisterAiService
@Singleton
public interface SummarizerAiService {

    @SystemMessage("You are an assistant that receives the content of a web page and sums up the text on that page. Add key takeaways to the end of the sum-up.")
    @UserMessage("""
                Here's the text: '{text}'
            """)
    Multi<String> summarize(@V("text") String text);
}
