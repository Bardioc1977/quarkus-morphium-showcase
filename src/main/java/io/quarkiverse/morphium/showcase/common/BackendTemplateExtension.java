package io.quarkiverse.morphium.showcase.common;

import io.quarkus.qute.TemplateExtension;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;

/**
 * Qute namespace extension providing {backend:*} expressions in all templates.
 *
 * Usage in templates:
 *   {backend:displayName}          -> "MongoDB", "Azure CosmosDB", "In-Memory", etc.
 *   {backend:isCosmosDB}           -> true/false
 *   {backend:supportsTransactions} -> true/false
 *   {backend:supportsCapped}       -> true/false
 */
@ApplicationScoped
@TemplateExtension(namespace = "backend")
public class BackendTemplateExtension {

    private static BackendInfoProvider provider;

    @Inject
    BackendInfoProvider backendInfoProvider;

    void onStart(@Observes StartupEvent ev) {
        provider = backendInfoProvider;
    }

    static String displayName() {
        if (provider == null) return "Unknown";
        return provider.getBackendDisplayName();
    }

    static boolean isCosmosDB() {
        if (provider == null) return false;
        return provider.isCosmosDB();
    }

    static boolean supportsTransactions() {
        if (provider == null) return true;
        return provider.isFeatureSupported("transactions");
    }

    static boolean supportsCapped() {
        if (provider == null) return true;
        return provider.isFeatureSupported("capped");
    }
}
