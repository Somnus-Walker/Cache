package cache.CacheBuilder;

import cache.Cache;

public interface CacheBuilder<K, V> {
    public CacheBuilder<K, V> put();

    public CacheBuilder<K, V> get();

    public CacheBuilder<K, V> clear();

    public Cache<K, V> build();
}


