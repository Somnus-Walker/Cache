package cache;

import cache.CacheBuilder.CBImpl.DiskCacheBuilder;
import cache.CacheBuilder.CacheBuilder;
import cache.CacheBuilder.CacheEngineer;

public class Test {
    public static void main(String[] args) {
        CacheBuilder<Integer, Integer> builder = new DiskCacheBuilder<>();
        CacheEngineer<Integer, Integer> cacheEngineer = new CacheEngineer<>(builder);
        Cache<Integer, Integer> cache = cacheEngineer.manufactureCache();
    }
}
