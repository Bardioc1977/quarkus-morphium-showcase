package io.quarkiverse.morphium.showcase.analytics.entity;

import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.Index;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.List;

@Entity(collectionName = "sales_records")
@Index({"region, product_name"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class SalesRecord {

    @Id
    private MorphiumId id;

    @Index
    private String productName;

    private double unitPrice;

    private int quantity;

    private double discount;

    private String region;

    private String salesRep;

    @Index
    private LocalDateTime saleDate;

    private List<String> categories;
}
