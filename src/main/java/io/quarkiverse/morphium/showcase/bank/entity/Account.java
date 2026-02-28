package io.quarkiverse.morphium.showcase.bank.entity;

import de.caluga.morphium.annotations.CreationTime;
import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.Index;
import de.caluga.morphium.annotations.Version;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Entity(collectionName = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Account {

    @Id
    private MorphiumId id;

    @Index(options = {"unique:1"})
    private String accountNumber;

    private String ownerName;

    private double balance;

    @Builder.Default
    private String currency = "EUR";

    @Version
    private Long version;

    @CreationTime
    private LocalDateTime createdAt;
}
