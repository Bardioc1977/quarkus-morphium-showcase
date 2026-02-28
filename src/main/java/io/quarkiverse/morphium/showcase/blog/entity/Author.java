package io.quarkiverse.morphium.showcase.blog.entity;

import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.Index;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Entity(collectionName = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Author {

    @Id
    private MorphiumId id;

    @Index(options = {"unique:1"})
    private String username;

    private String displayName;

    private String email;

    private String bio;
}
