package io.quarkiverse.morphium.showcase.audit;

import de.caluga.morphium.Morphium;
import io.quarkiverse.morphium.showcase.audit.entity.AuditEntry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuditListener {

    @Inject
    Morphium morphium;

    public void log(String entityType, String entityId, String action, String details, String user) {
        AuditEntry entry = AuditEntry.builder()
                .entityType(entityType)
                .entityId(entityId)
                .action(action)
                .details(details)
                .user(user)
                .build();
        morphium.store(entry);
    }
}
