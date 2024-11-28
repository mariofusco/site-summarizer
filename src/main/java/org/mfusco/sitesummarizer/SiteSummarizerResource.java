package org.mfusco.sitesummarizer;

import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/summarize")
public class SiteSummarizerResource {

    @Inject
    private SiteSummarizer siteSummarizer;

    @Path("/{type}/{topic}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Multi<String> read(@PathParam("type") String type, @PathParam("topic") String topic) {
        return siteSummarizer.summarize(SiteType.determineType(type), topic);
    }
}