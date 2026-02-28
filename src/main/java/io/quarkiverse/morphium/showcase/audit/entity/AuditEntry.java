package io.quarkiverse.morphium.showcase.audit.entity;

import de.caluga.morphium.annotations.Capped;
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

@Entity(collectionName = "audit_log")
@Capped(maxSize = 10485760, maxEntries = 10000)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class AuditEntry {

    @Id
    private MorphiumId id;

    private String entityType;

    private String entityId;

    /**
     * Action performed: CREATE, UPDATE, or DELETE.
     */
    private String action;

    private String details;

    private String user;

    @CreationTime
    private LocalDateTime timestamp;
}
