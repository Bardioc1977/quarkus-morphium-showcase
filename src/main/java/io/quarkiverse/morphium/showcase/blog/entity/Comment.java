package io.quarkiverse.morphium.showcase.blog.entity;

import de.caluga.morphium.annotations.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Embedded
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Comment {
    private String author;
    private String text;
    private LocalDateTime createdAt;
}
