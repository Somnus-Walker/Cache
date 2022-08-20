package MemoryCache;

import CacheInterface.Cache;
import Exceptions.NullElementException;
import org.checkerframework.checker.nullness.Opt;

import java.util.LinkedHashMap;
import java.util.*;


public class MemoryCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V>
    {
        private final int cacheSize;


        public MemoryCache(int cacheSize)
            {
                super(cacheSize, 0.75F, true);
                this.cacheSize = cacheSize;
            }

        @Override
        public void putKeyAndValue(K key, V value) throws NullElementException
            {
                if (key == null || value == null) throw new NullElementException("Element can't be null");
                super.put(key, value);
            }


        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
            {
                return this.size() > this.cacheSize;
            }


        @Override
        public Optional<V> getValueByKey(K key)
            {
                return Optional.ofNullable(super.get(key));
            }

        @Override
        public void clearCache()
            {
                super.clear();
            }


        @Override
        public String toString()
            {
                return "Cache Map: " + this.entrySet();
            }

    }

