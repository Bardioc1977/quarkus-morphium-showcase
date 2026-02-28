package io.quarkiverse.morphium.showcase.bank.entity;

import de.caluga.morphium.annotations.CreationTime;
import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Entity(collectionName = "transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Transfer {

    @Id
    private MorphiumId id;

    private String fromAccount;

    private String toAccount;

    private double amount;

    private String currency;

    private String description;

    @CreationTime
    private LocalDateTime createdAt;

    /**
     * Status of the transfer: PENDING, COMPLETED, or FAILED.
     */
    private String status;
}
