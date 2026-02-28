package io.quarkiverse.morphium.showcase.analytics;

import io.quarkiverse.morphium.showcase.common.DocLink;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/analytics")
public class AnalyticsResource {

    @Inject
    Template analytics;

    @Inject
    AnalyticsService analyticsService;

    private static final List<DocLink> DOC_LINKS = List.of(
            new DocLink("/docs/howtos/aggregation-examples", "Aggregation Examples", "$group, $match, $sort, $project"),
            new DocLink("/docs/api-reference", "API Reference", "Aggregator, Expr, distinct()")
    );

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance page() {
        analyticsService.seedData();
        return analytics.data("active", "analytics")
                .data("salesByRegion", analyticsService.salesByRegion())
                .data("salesByProduct", analyticsService.salesByProduct())
                .data("monthlySales", analyticsService.monthlySales())
                .data("topSalesReps", analyticsService.topSalesReps())
                .data("totalRecords", analyticsService.totalRecords())
                .data("distinctRegions", analyticsService.distinctRegions())
                .data("distinctProducts", analyticsService.distinctProducts())
                .data("docLinks", DOC_LINKS);
    }
}
