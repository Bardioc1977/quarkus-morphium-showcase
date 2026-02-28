package io.quarkiverse.morphium.showcase.messaging;

import io.quarkiverse.morphium.showcase.common.DocLink;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/messaging")
public class MessagingResource {

    @Inject
    Template messaging;

    @Inject
    MessagingService messagingService;

    private static final List<DocLink> DOC_LINKS = List.of(
            new DocLink("/docs/messaging", "Messaging System", "Built-in Queue, Topics, Listeners"),
            new DocLink("/docs/howtos/messaging-implementations", "Messaging Implementations", "sendMessage(), addListenerForTopic()"),
            new DocLink("/docs/api-reference", "API Reference", "query.set(), countAll(), sort()")
    );

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance page(@QueryParam("topic") String topic) {
        messagingService.seedData();
        var messages = (topic != null && !topic.isBlank())
                ? messagingService.findByTopic(topic)
                : messagingService.findAll();
        return messaging.data("active", "messaging")
                .data("messages", messages)
                .data("topics", messagingService.getTopics())
                .data("count", messagingService.count())
                .data("selectedTopic", topic)
                .data("docLinks", DOC_LINKS);
    }

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response sendMessage(
            @FormParam("sender") String sender,
            @FormParam("recipient") String recipient,
            @FormParam("topic") String topic,
            @FormParam("text") String text) {
        messagingService.send(sender, recipient, topic, text);
        return Response.seeOther(URI.create("/messaging")).build();
    }

    @POST
    @Path("/read/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response markAsRead(@PathParam("id") String id) {
        messagingService.markAsRead(id);
        return Response.seeOther(URI.create("/messaging")).build();
    }

    @POST
    @Path("/seed")
    @Produces(MediaType.TEXT_HTML)
    public Response seed() {
        messagingService.deleteAll();
        messagingService.seedData();
        return Response.seeOther(URI.create("/messaging")).build();
    }
}
