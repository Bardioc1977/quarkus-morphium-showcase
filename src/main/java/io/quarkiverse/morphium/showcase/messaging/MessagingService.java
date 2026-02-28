package io.quarkiverse.morphium.showcase.messaging;

import de.caluga.morphium.Morphium;
import de.caluga.morphium.driver.MorphiumId;
import io.quarkiverse.morphium.showcase.messaging.entity.ChatMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class MessagingService {

    @Inject
    Morphium morphium;

    public List<ChatMessage> findAll() {
        return morphium.createQueryFor(ChatMessage.class)
                .sort(Map.of(ChatMessage.Fields.sentAt, -1))
                .asList();
    }

    public List<ChatMessage> findByTopic(String topic) {
        return morphium.createQueryFor(ChatMessage.class)
                .f(ChatMessage.Fields.topic).eq(topic)
                .sort(Map.of(ChatMessage.Fields.sentAt, -1))
                .asList();
    }

    public ChatMessage send(String sender, String recipient, String topic, String text) {
        ChatMessage message = ChatMessage.builder()
                .sender(sender)
                .recipient(recipient)
                .topic(topic)
                .text(text)
                .read(false)
                .build();
        morphium.store(message);
        return message;
    }

    public void markAsRead(String id) {
        var query = morphium.createQueryFor(ChatMessage.class)
                .f(ChatMessage.Fields.id).eq(new MorphiumId(id));
        query.set(ChatMessage.Fields.read, true, false, false, null);
    }

    public void deleteAll() {
        morphium.dropCollection(ChatMessage.class);
    }

    public long count() {
        return morphium.createQueryFor(ChatMessage.class).countAll();
    }

    public long countByTopic(String topic) {
        return morphium.createQueryFor(ChatMessage.class)
                .f(ChatMessage.Fields.topic).eq(topic)
                .countAll();
    }

    public List<String> getTopics() {
        return morphium.createQueryFor(ChatMessage.class)
                .asList()
                .stream()
                .map(ChatMessage::getTopic)
                .distinct()
                .collect(Collectors.toList());
    }

    public void seedData() {
        if (count() > 0) return;

        send("Alice", "Bob", "general", "Hey Bob, how is the project going?");
        send("Bob", "Alice", "general", "Hi Alice! It's going well, almost done with the backend.");
        send("Charlie", "Alice", "tech-support", "Alice, I need help with the deployment pipeline.");
        send("Alice", "Charlie", "tech-support", "Sure Charlie, let me take a look at the CI config.");
        send("Bob", "Charlie", "general", "Charlie, want to join the standup tomorrow?");
    }
}
