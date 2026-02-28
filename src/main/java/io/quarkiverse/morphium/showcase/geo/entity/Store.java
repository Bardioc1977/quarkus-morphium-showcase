package io.quarkiverse.morphium.showcase.geo.entity;

import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.Index;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Entity(collectionName = "stores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Store {

    @Id
    private MorphiumId id;

    @Index
    private String name;

    private String address;

    private String city;

    private String country;

    /**
     * Geospatial coordinates as [longitude, latitude] for 2dsphere index.
     */
    private double[] location;

    private String phone;

    private List<String> services;
}
