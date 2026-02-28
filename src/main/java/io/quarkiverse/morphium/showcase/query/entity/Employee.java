package io.quarkiverse.morphium.showcase.query.entity;

import de.caluga.morphium.annotations.Aliases;
import de.caluga.morphium.annotations.CreationTime;
import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.IgnoreNullFromDB;
import de.caluga.morphium.annotations.Index;
import de.caluga.morphium.annotations.Transient;
import de.caluga.morphium.annotations.lifecycle.Lifecycle;
import de.caluga.morphium.annotations.lifecycle.PostLoad;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.List;

@Entity(collectionName = "employees")
@Index({"lastName, firstName"})
@Lifecycle
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Employee {

    @Id
    private MorphiumId id;

    private String firstName;

    private String lastName;

    @Aliases({"mail", "e_mail"})
    private String email;

    @Index
    private String department;

    private String position;

    private double salary;

    private LocalDateTime hireDate;

    @Transient
    private transient String fullName;

    @IgnoreNullFromDB
    private List<String> skills;

    private boolean active;

    @PostLoad
    public void onPostLoad() {
        this.fullName = firstName + " " + lastName;
    }
}
