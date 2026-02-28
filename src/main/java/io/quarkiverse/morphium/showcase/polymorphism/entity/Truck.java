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
public class Truck extends Vehicle {

    private double payloadTons;

    private int axles;

    private boolean hasTowbar;
}
