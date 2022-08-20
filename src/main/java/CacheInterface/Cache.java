package CacheInterface;


import Exceptions.NullElementException;

import java.io.IOException;
import java.util.Optional;

public interface Cache<K, V>
    {
        void putKeyAndValue(K key, V value) throws NullElementException, IOException;

        Optional<V> getValueByKey(K key) throws IOException;


        void clearCache();
    }
