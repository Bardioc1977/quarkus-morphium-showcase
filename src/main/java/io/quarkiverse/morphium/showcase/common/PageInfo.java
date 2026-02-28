package io.quarkiverse.morphium.showcase.common;

public record PageInfo(String title, String active) {
    public static PageInfo of(String title, String active) {
        return new PageInfo(title, active);
    }
}
