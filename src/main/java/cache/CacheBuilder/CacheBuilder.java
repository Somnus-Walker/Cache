package cache.CacheBuilder;

import cache.Cache;

public interface CacheBuilder<K, V> {

     CacheBuilder<K,V> cacheSize(int size);

     Cache<K, V> build();
}
