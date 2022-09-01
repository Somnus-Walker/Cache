package cache.CacheBuilder.CacheEngineer;

import cache.Cache;
import cache.CacheBuilder.CacheBuilder;

public class CacheEngineer<K, V> {
    private final CacheBuilder<K, V> builder;

    public CacheEngineer(CacheBuilder<K, V> builder) {
        this.builder = builder;
        if (this.builder == null) {
            throw new IllegalArgumentException("Cant build without a builder");
        }
    }

    public Cache<K, V> manufactureCache(int cacheSize) {
        return builder.cacheSize(cacheSize).build();
    }
}
