package io.quarkiverse.morphium.showcase.polymorphism.entity;

import de.caluga.morphium.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Entity(collectionName = "vehicles", polymorph = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Car extends Vehicle {

    private int doors;

    private String fuelType;

    private boolean convertible;
}
