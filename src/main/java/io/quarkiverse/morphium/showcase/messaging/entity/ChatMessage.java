package io.quarkiverse.morphium.showcase.messaging.entity;

import de.caluga.morphium.annotations.CreationTime;
import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Entity(collectionName = "chat_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class ChatMessage {

    @Id
    private MorphiumId id;

    private String sender;

    private String recipient;

    private String topic;

    private String text;

    @CreationTime
    private LocalDateTime sentAt;

    private boolean read;
}
