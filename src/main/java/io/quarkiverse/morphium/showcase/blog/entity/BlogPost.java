package io.quarkiverse.morphium.showcase.blog.entity;

import de.caluga.morphium.annotations.*;
import de.caluga.morphium.annotations.lifecycle.Lifecycle;
import de.caluga.morphium.annotations.lifecycle.PreStore;
import de.caluga.morphium.annotations.lifecycle.PostLoad;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(collectionName = "blog_posts")
@Lifecycle
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class BlogPost {

    @Id
    private MorphiumId id;

    @Index
    private String title;

    private String content;

    @Version
    private Long version;

    @CreationTime
    private LocalDateTime createdAt;

    @LastChange
    private LocalDateTime lastChanged;

    @LastAccess
    private LocalDateTime lastAccessed;

    @Reference
    private Author author;

    @Reference(lazyLoading = true)
    private Author reviewer;

    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default
    private List<String> tags = new ArrayList<>();

    @Builder.Default
    private Map<String, String> metadata = new HashMap<>();

    private boolean published;

    @Transient
    private transient String editNote;

    @PreStore
    public void onPreStore() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        if (metadata == null) {
            metadata = new HashMap<>();
        }
    }

    @PostLoad
    public void onPostLoad() {
        // Could perform validation or enrichment after loading
    }
}
