package io.quarkiverse.morphium.showcase.catalog.entity;

import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.Index;
import de.caluga.morphium.annotations.Property;
import de.caluga.morphium.annotations.caching.Cache;
import de.caluga.morphium.annotations.caching.WriteBuffer;
import de.caluga.morphium.annotations.WriteSafety;
import de.caluga.morphium.annotations.SafetyLevel;
import de.caluga.morphium.annotations.DefaultReadPreference;
import de.caluga.morphium.annotations.ReadPreferenceLevel;
import de.caluga.morphium.driver.MorphiumId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

@Entity(collectionName = "products")
@Index({"-price, name"})
@Cache(maxEntries = 100, strategy = Cache.ClearStrategy.LRU, timeout = 30000)
@WriteSafety(level = SafetyLevel.NORMAL)
@DefaultReadPreference(ReadPreferenceLevel.PRIMARY)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Product {

    @Id
    private MorphiumId id;

    @Index
    private String name;

    @Property(fieldName = "product_description")
    private String description;

    @Index
    private double price;

    private int stock;

    private Category category;

    private java.util.List<String> tags;
}
