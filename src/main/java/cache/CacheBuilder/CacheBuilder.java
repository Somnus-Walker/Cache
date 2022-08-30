package cache.CacheBuilder;


import cache.exceptions.NullElementException;

import java.util.Optional;


public interface CacheBuilder<K, V> {

    public CacheBuilder putKeyAndValue( K key, V value) throws NullElementException;

   public CacheBuilder getValueByKey(K key);

    public CacheBuilder clearCache();
}
