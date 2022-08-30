import cache.CacheBuilder.CacheBuilder;
import cache.exceptions.NullElementException;
import cache.CacheImpl.MemoryCacheBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestMemoryCacheBuilder {
    CacheBuilder<Integer, Integer> cacheBuilder;

    @BeforeEach
    void setup() throws NullElementException {
        cacheBuilder = new MemoryCacheBuilder<Integer, Integer>(3);
        cacheBuilder.putKeyAndValue(1, 1);
        cacheBuilder.putKeyAndValue(2, 2);
        cacheBuilder.putKeyAndValue(3, 3);
    }

    @Test
    void LRUCacheTest() throws NullElementException {
        assertAll("put all values", () -> assertEquals(Optional.of(1), cacheBuilder.getValueByKey(1)), () -> assertEquals(Optional.of(2), cacheBuilder.getValueByKey(2)), () -> assertEquals(Optional.of(3), cacheBuilder.getValueByKey(3)));

        cacheBuilder.putKeyAndValue(4, 4);
        assertEquals(Optional.empty(), cacheBuilder.getValueByKey(1));

        assertEquals(Optional.of(4), cacheBuilder.getValueByKey(4));


    }

    @Test
    void LRUUpdateValueTest() throws NullElementException {
        cacheBuilder.putKeyAndValue(1, 5);
        cacheBuilder.putKeyAndValue(4, 4);


        assertAll("put all values", () -> assertEquals(Optional.of(5), cacheBuilder.getValueByKey(1)), () -> assertEquals(Optional.of(3), cacheBuilder.getValueByKey(3)), () -> assertEquals(Optional.of(4), cacheBuilder.getValueByKey(4)));
    }

    @Test
    void clearCacheTest() {
        cacheBuilder.clearCache();

        assertEquals(Optional.empty(), cacheBuilder.getValueByKey(1));
        assertEquals(Optional.empty(), cacheBuilder.getValueByKey(2));
        assertEquals(Optional.empty(), cacheBuilder.getValueByKey(3));
    }
}
