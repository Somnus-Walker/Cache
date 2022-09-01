import cache.Cache;
import cache.builders.MemoryCacheBuilder;
import cache.CacheBuilder.CacheBuilder;
import cache.CacheBuilder.CacheEngineer.CacheEngineer;
import cache.exceptions.NullElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestMemoryCache
{
    Cache<Integer, Integer> cache;

    @BeforeEach
    void setup() throws NullElementException
    {
        CacheBuilder<Integer, Integer> cacheBuilder = new MemoryCacheBuilder<>();
        CacheEngineer<Integer, Integer> cacheEngineer = new CacheEngineer<>(cacheBuilder);
        cache = cacheEngineer.manufactureCache();
    }

    @Test
    void LRUCacheTest() throws NullElementException
    {
        assertAll("put all values", () -> assertEquals(Optional.of(1), cache.getValueByKey(1)), () -> assertEquals(Optional.of(2), cache.getValueByKey(2)), () -> assertEquals(Optional.of(3), cache.getValueByKey(3)));

        cache.putKeyAndValue(4, 4);
        assertEquals(Optional.empty(), cache.getValueByKey(1));

        assertEquals(Optional.of(4), cache.getValueByKey(4));


    }

    @Test
    void LRUUpdateValueTest() throws NullElementException
    {
        cache.putKeyAndValue(1, 5);
        cache.putKeyAndValue(4, 4);


        assertAll("put all values", () -> assertEquals(Optional.of(5), cache.getValueByKey(1)), () -> assertEquals(Optional.of(3), cache.getValueByKey(3)), () -> assertEquals(Optional.of(4), cache.getValueByKey(4)));
    }

    @Test
    void clearCacheTest()
    {
        cache.clearCache();

        assertEquals(Optional.empty(), cache.getValueByKey(1));
        assertEquals(Optional.empty(), cache.getValueByKey(2));
        assertEquals(Optional.empty(), cache.getValueByKey(3));
    }
}
