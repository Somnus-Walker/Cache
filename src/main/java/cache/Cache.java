package cache;


import exceptions.NullElementException;

import java.io.IOException;
import java.util.Optional;

public interface Cache<K, V> {
    void putKeyAndValue( K key, V value) throws NullElementException;

    Optional<V> getValueByKey(K key);


    void clearCache();
}
