package io.quarkiverse.morphium.showcase.common;

import de.caluga.morphium.Morphium;
import de.caluga.morphium.driver.BackendType;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

/**
 * CDI bean that caches the detected backend type from the Morphium driver.
 * Provides helper methods for template extensions and resource classes.
 */
@ApplicationScoped
public class BackendInfoProvider {

    @Inject
    Morphium morphium;

    private volatile BackendType cachedType;

    void onStart(@Observes StartupEvent ev) {
        cachedType = morphium.getBackendType();
    }

    public BackendType getBackendType() {
        if (cachedType == null) {
            cachedType = morphium.getBackendType();
        }
        return cachedType;
    }

    public boolean isCosmosDB() {
        return getBackendType() == BackendType.COSMOSDB;
    }

    public boolean isFeatureSupported(String feature) {
        if (!isCosmosDB()) return true;
        return switch (feature) {
            case "transactions", "mapReduce", "capped" -> false;
            default -> true;
        };
    }

    public String getBackendDisplayName() {
        return switch (getBackendType()) {
            case COSMOSDB -> "Azure CosmosDB";
            case IN_MEMORY -> "In-Memory";
            case MORPHIUM_SERVER -> "MorphiumServer";
            case MONGODB -> "MongoDB";
        };
    }
}
