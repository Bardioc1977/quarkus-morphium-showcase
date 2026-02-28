package io.quarkiverse.morphium.showcase.docs;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/docs")
public class DocsResource {

    @Inject
    Template docs;

    @Inject
    @io.quarkus.qute.Location("docs-page.html")
    Template docsPage;

    @Inject
    MarkdownService markdownService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance hub() {
        List<DocEntry> allDocs = markdownService.listDocs();
        Map<String, List<DocEntry>> grouped = allDocs.stream()
                .collect(Collectors.groupingBy(DocEntry::category, LinkedHashMap::new, Collectors.toList()));
        return docs.data("active", "docs")
                .data("docsByCategory", grouped)
                .data("totalDocs", allDocs.size());
    }

    @GET
    @Path("/{slug:.+}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance page(@PathParam("slug") String slug) {
        String html = markdownService.renderDoc(slug).orElse("<p>Document not found: " + slug + "</p>");
        String title = markdownService.extractTitle(slug).orElse(slug);
        return docsPage.data("active", "docs")
                .data("docHtml", html)
                .data("docTitle", title)
                .data("slug", slug);
    }
}
