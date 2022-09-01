<<<<<<< HEAD:src/main/java/cache/CacheBuilder/builders/DiskCacheBuilder.java
package cache.CacheBuilder.builders;
=======
package cache.CacheBuilder.builders
>>>>>>> 26e03d8f357ca5f957dcffb83c9f28b5eb4ad1de:src/main/java/cache/CacheBuilder/CBImpl/DiskCacheBuilder.java

import cache.Cache;
import cache.CacheBuilder.CacheBuilder;
import cache.CacheImpl.DiskCache;

import java.io.Serializable;

public class DiskCacheBuilder<K, V extends Serializable> implements CacheBuilder<K, V> {
   private int cacheSize;


    @Override
    public DiskCacheBuilder<K, V> cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }

    public Cache<K, V> build() {
        return new DiskCache<K, V>(cacheSize);
    }
}
