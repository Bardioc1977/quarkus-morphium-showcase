package io.quarkiverse.morphium.showcase.importer;

import de.caluga.morphium.Morphium;
import de.caluga.morphium.SequenceGenerator;
import de.caluga.morphium.driver.MorphiumId;
import de.caluga.morphium.query.Query;
import io.quarkiverse.morphium.showcase.importer.entity.ImportRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ImportService {

    @Inject
    Morphium morphium;

    public List<ImportRecord> findAll(int limit) {
        return morphium.createQueryFor(ImportRecord.class)
                .sort(Map.of(ImportRecord.Fields.importNumber, -1))
                .limit(limit)
                .asList();
    }

    public long count() {
        return morphium.createQueryFor(ImportRecord.class).countAll();
    }

    public long bulkImport(int count) {
        SequenceGenerator seq = new SequenceGenerator(morphium, "import_number", 1, 1);
        List<ImportRecord> records = new ArrayList<>();
        String[] sources = {"API", "CSV", "FTP", "S3", "MANUAL"};
        String[] statuses = {"PENDING", "PROCESSED", "ERROR"};

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            records.add(ImportRecord.builder()
                    .importNumber(seq.getNextValue())
                    .source(sources[i % sources.length])
                    .data("Bulk import record #" + (i + 1))
                    .status(statuses[i % statuses.length])
                    .tags(List.of("bulk", "auto-generated"))
                    .build());
        }

        morphium.storeList(records);

        return System.currentTimeMillis() - start;
    }

    public List<ImportRecord> findByStatus(String status) {
        return morphium.createQueryFor(ImportRecord.class)
                .f(ImportRecord.Fields.status).eq(status)
                .asList();
    }

    public void addTag(String id, String tag) {
        Query<ImportRecord> query = morphium.createQueryFor(ImportRecord.class)
                .f(ImportRecord.Fields.id).eq(new MorphiumId(id));
        morphium.push(query, ImportRecord.Fields.tags, tag);
    }

    public void removeTag(String id, String tag) {
        Query<ImportRecord> query = morphium.createQueryFor(ImportRecord.class)
                .f(ImportRecord.Fields.id).eq(new MorphiumId(id));
        morphium.pull(query, ImportRecord.Fields.tags, tag);
    }

    public void markProcessed(String id) {
        Query<ImportRecord> query = morphium.createQueryFor(ImportRecord.class)
                .f(ImportRecord.Fields.id).eq(new MorphiumId(id));
        query.set(ImportRecord.Fields.status, "PROCESSED", false, false, null);
    }

    public void unsetSource(String id) {
        Query<ImportRecord> query = morphium.createQueryFor(ImportRecord.class)
                .f(ImportRecord.Fields.id).eq(new MorphiumId(id));
        query.unset(ImportRecord.Fields.source);
    }

    public void deleteAll() {
        morphium.dropCollection(ImportRecord.class);
    }

    public void seedData() {
        if (count() > 0) return;

        SequenceGenerator seq = new SequenceGenerator(morphium, "import_number", 1, 1);

        String[] sources = {"API", "CSV", "FTP", "S3", "MANUAL"};
        String[] statuses = {"PENDING", "PROCESSED", "ERROR"};
        List<ImportRecord> records = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            records.add(ImportRecord.builder()
                    .importNumber(seq.getNextValue())
                    .source(sources[i % sources.length])
                    .data("Sample data record #" + (i + 1))
                    .status(statuses[i % statuses.length])
                    .tags(List.of("seed", sources[i % sources.length].toLowerCase()))
                    .build());
        }

        morphium.storeList(records);
    }
}
