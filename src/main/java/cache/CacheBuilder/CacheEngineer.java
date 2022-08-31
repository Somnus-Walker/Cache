package cache.CacheBuilder;

import cache.Cache;

public class CacheEngineer<K, V> {
    private final CacheBuilder<K, V> builder;

    public CacheEngineer(CacheBuilder<K, V> builder) {
        this.builder = builder;
        if (this.builder == null) {
            throw new IllegalArgumentException("Cant build without cache");
        }
    }

    public Cache<K, V> manufactureCache() {
        return builder.cacheSize(2).build();
    }
}
