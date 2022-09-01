package cache.caches;

import cache.Cache;
import cache.CacheEngineer;
import cache.builders.CacheBuilder;
import cache.builders.DiskCacheBuilder;
import cache.exceptions.NullElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestDiskCache
{
    Cache<Integer, Integer> diskCache;

    @BeforeEach
    void setup() throws NullElementException
    {
        CacheBuilder<Integer, Integer> cacheBuilder = new DiskCacheBuilder<>();
        CacheEngineer<Integer, Integer> cacheEngineer = new CacheEngineer<>(cacheBuilder);
        diskCache = cacheEngineer.manufactureCache(3);
    }

    @Test
    void getAndEvictTest() throws NullElementException
    {
        assertAll("Getting elements", () -> assertEquals(Optional.of(1), diskCache.getValueByKey(1)), () -> assertEquals(Optional.of(2), diskCache.getValueByKey(2)), () -> assertEquals(Optional.of(3), diskCache.getValueByKey(3)));

        diskCache.putKeyAndValue(4, 4);

        assertEquals(Optional.empty(), diskCache.getValueByKey(1));
        assertEquals(Optional.of(4), diskCache.getValueByKey(4));
    }

    @Test
    void updateTest() throws NullElementException
    {
        diskCache.putKeyAndValue(1, 12);
        assertEquals(Optional.of(12), diskCache.getValueByKey(1));

        diskCache.putKeyAndValue(30, 31);

        assertEquals(Optional.empty(), diskCache.getValueByKey(2));
        assertEquals(Optional.of(12), diskCache.getValueByKey(1));
        assertEquals(Optional.of(31), diskCache.getValueByKey(30));
    }

    @Test
    void clearCacheTest()
    {
        diskCache.clearCache();

        assertEquals(Optional.empty(), diskCache.getValueByKey(1));
        assertEquals(Optional.empty(), diskCache.getValueByKey(2));
        assertEquals(Optional.empty(), diskCache.getValueByKey(3));
    }

    public static void main(String[] args)
    {
        Cache<Integer, Integer> cache = new DiskCache<>(3);
        cache.clearCache();
    }
}