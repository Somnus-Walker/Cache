package cache.CacheBuilder.CBImpl;

import cache.Cache;
import cache.CacheBuilder.CacheBuilder;
import cache.CacheImpl.DiskCache;

import java.io.Serializable;

public class DiskCacheBuilder<K, V extends Serializable> implements CacheBuilder<K, V> {
    private int cacheSize;


    @Override
    public DiskCacheBuilder<K, V> cacheSize(int size) {
        this.cacheSize = size;
        return this;
    }

    public Cache<K, V> build() {
        return new DiskCache<>(cacheSize);
    }
}
