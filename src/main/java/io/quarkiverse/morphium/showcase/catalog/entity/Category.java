package io.quarkiverse.morphium.showcase.catalog.entity;

import de.caluga.morphium.annotations.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Embedded
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Category {
    private String name;
    private String description;
}
