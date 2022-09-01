package cache.CacheImpl;

import cache.Cache;
import exceptions.NullElementException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


public final class MemoryCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {
    private static final float LOAD_FACTOR = 0.75F;
    private final int cacheSize;

    public MemoryCache(final int cacheSize) {
        super(cacheSize, LOAD_FACTOR, true);

        this.cacheSize = cacheSize;
    }

    @Override
    public void putKeyAndValue(final K key, final V value) throws NullElementException {
        if (key == null || value == null) {
            throw new NullElementException("Element can't be null");
        }
        super.put(key, value);
    }


    @Override
    protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
        return this.size() > this.cacheSize;
    }


    @Override
    public Optional<V> getValueByKey(final K key) {
        return Optional.ofNullable(super.get(key));
    }

    @Override
    public void clearCache() {
        super.clear();
    }


    @Override
    public String toString() {
        return "Cache Map: " + this.entrySet();
    }

}

