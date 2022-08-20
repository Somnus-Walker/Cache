import CacheInterface.Cache;
import Exceptions.NullElementException;
import MemoryCache.MemoryCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestCache
    {
        Cache<Integer, Integer> cache;

        @BeforeEach
        void setup() throws NullElementException, IOException
            {
                cache = new MemoryCache<Integer, Integer>(3);
                cache.putKeyAndValue(1, 1);
                cache.putKeyAndValue(2, 2);
                cache.putKeyAndValue(3, 3);
            }

        @Test
        void LRUCacheTest() throws NullElementException, IOException
            {
                assertAll("put all values",
                        () -> assertEquals(Optional.of(1), cache.getValueByKey(1)),
                        () -> assertEquals(Optional.of(2), cache.getValueByKey(2)),
                        () -> assertEquals(Optional.of(3), cache.getValueByKey(3))
                );

                cache.putKeyAndValue(4, 4);
                assertEquals(Optional.empty(), cache.getValueByKey(1));

                assertEquals(Optional.of(4), cache.getValueByKey(4));


            }

        @Test
        void LRUUpdateValueTest() throws NullElementException, IOException

            {
                cache.putKeyAndValue(1, 5);
                cache.putKeyAndValue(4, 4);


                assertAll("put all values",
                        () -> assertEquals(Optional.of(5), cache.getValueByKey(1)),
                        () -> assertEquals(Optional.of(3), cache.getValueByKey(3)),
                        () -> assertEquals(Optional.of(4), cache.getValueByKey(4)));
            }
    }
