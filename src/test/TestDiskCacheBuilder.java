import cache.CacheBuilder.CacheBuilder;
import cache.CacheImpl.DiskCacheBuilder;
import cache.exceptions.NullElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestDiskCacheBuilder {
    CacheBuilder<Integer, Integer> diskCacheBuilder;

    @BeforeEach
    void setup() throws NullElementException {
        diskCacheBuilder = new DiskCacheBuilder<>(3);
        diskCacheBuilder.putKeyAndValue(1, 1);
        diskCacheBuilder.putKeyAndValue(2, 2);
        diskCacheBuilder.putKeyAndValue(3, 3);
    }

    @Test
    void getAndEvictTest() throws NullElementException {
        assertAll("Getting elements", () -> assertEquals(Optional.of(1), diskCacheBuilder.getValueByKey(1)), () -> assertEquals(Optional.of(2), diskCacheBuilder.getValueByKey(2)), () -> assertEquals(Optional.of(3), diskCacheBuilder.getValueByKey(3)));

        diskCacheBuilder.putKeyAndValue(4, 4);

        assertEquals(Optional.empty(), diskCacheBuilder.getValueByKey(1));
        assertEquals(Optional.of(4), diskCacheBuilder.getValueByKey(4));
    }

    @Test
    void updateTest() throws NullElementException {
        diskCacheBuilder.putKeyAndValue(1, 12);
        assertEquals(Optional.of(12), diskCacheBuilder.getValueByKey(1));

        diskCacheBuilder.putKeyAndValue(30, 31);

        assertEquals(Optional.empty(), diskCacheBuilder.getValueByKey(2));
        assertEquals(Optional.of(12), diskCacheBuilder.getValueByKey(1));
        assertEquals(Optional.of(31), diskCacheBuilder.getValueByKey(30));
    }

    @Test
    void clearCacheTest() {
        diskCacheBuilder.clearCache();

        assertEquals(Optional.empty(), diskCacheBuilder.getValueByKey(1));
        assertEquals(Optional.empty(), diskCacheBuilder.getValueByKey(2));
        assertEquals(Optional.empty(), diskCacheBuilder.getValueByKey(3));
    }

    public static void main(String[] args) {
        CacheBuilder<Integer, Integer> cacheBuilder = new DiskCacheBuilder<>(3);
        cacheBuilder.clearCache();
    }
}
