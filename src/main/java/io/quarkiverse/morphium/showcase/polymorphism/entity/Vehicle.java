package io.quarkiverse.morphium.showcase.polymorphism.entity;

import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Entity(collectionName = "vehicles", polymorph = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Vehicle {

    @Id
    private MorphiumId id;

    private String manufacturer;

    private String model;

    private int year;

    private double price;

    public String getTypeName() {
        return getClass().getSimpleName();
    }
}
