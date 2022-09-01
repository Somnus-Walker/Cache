package cache.CacheBuilder.CacheEngineer;

import cache.Cache;
import cache.CacheBuilder.CacheBuilder;

public class CacheEngineer<K, V> {
    private  CacheBuilder<K, V> builder;

    public CacheEngineer(CacheBuilder<K, V> builder) {
        this.builder = builder;
        if (this.builder == null) {throw new IllegalArgumentException("Cant build without builder");}
    }

    public Cache<K, V> manufactureCache() {
        return builder.cacheSize(3).build();
    }
}
