package cache.CacheImpl;

import cache.CacheBuilder.CacheBuilder;
import cache.exceptions.NullElementException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


public final class MemoryCacheBuilder<K, V> extends LinkedHashMap<K, V> implements CacheBuilder<K, V> {
    private final int cacheSize;

    public MemoryCacheBuilder(final int cacheSize) {
        super(cacheSize, 0.75F, true);

        this.cacheSize = cacheSize;
    }

    @Override
    public CacheBuilder<K, V> putKeyAndValue(final K key, final V value) throws NullElementException {
        if (key == null || value == null) {
            throw new NullElementException("Element can't be null");
        }
        super.put(key, value);
        return this;
    }


    @Override
    protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
        return this.size() > this.cacheSize;
    }


    @Override
    public CacheBuilder<K, V> getValueByKey(final K key) {
        return this;
    }

    @Override
    public CacheBuilder<K, V> clearCache() {
        super.clear();
        return this;
    }


    @Override
    public String toString() {
        return "Cache Map: " + this.entrySet();
    }

}

