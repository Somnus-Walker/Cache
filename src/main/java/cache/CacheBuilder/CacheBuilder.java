package cache.CacheBuilder;

import cache.Cache;

public interface CacheBuilder<K, V> {

    public CacheBuilder<K,V> cacheSize(int size);

    public Cache<K, V> build();
}
