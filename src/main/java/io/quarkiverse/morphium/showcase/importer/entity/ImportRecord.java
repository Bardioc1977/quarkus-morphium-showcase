package io.quarkiverse.morphium.showcase.importer.entity;

import de.caluga.morphium.annotations.CreationTime;
import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.caching.WriteBuffer;
import de.caluga.morphium.driver.MorphiumId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.List;

@Entity(collectionName = "import_records")
@WriteBuffer(size = 500, strategy = WriteBuffer.STRATEGY.WRITE_NEW, timeout = 5000)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class ImportRecord {

    @Id
    private MorphiumId id;

    private long importNumber;

    private String source;

    private String data;

    /**
     * Status of the import: PENDING, PROCESSED, or ERROR.
     */
    private String status;

    @CreationTime
    private LocalDateTime importedAt;

    private List<String> tags;
}
