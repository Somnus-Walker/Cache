package cache.CacheBuilder.CBImpl;

import cache.Cache;
import cache.CacheBuilder.CacheBuilder;
import cache.CacheImpl.MemoryCache;

public class MemoryCacheBuilder<K, V> implements CacheBuilder<K, V> {
    private int cacheSize;

    @Override
    public MemoryCacheBuilder<K, V> cacheSize(int size) {
        this.cacheSize = size;
        return this;
    }

    public Cache<K, V> build() {
        return new MemoryCache<>(cacheSize);
    }
}
