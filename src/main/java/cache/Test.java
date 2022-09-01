package cache;

import cache.CacheBuilder.builders.DiskCacheBuilder;
import cache.CacheBuilder.CacheBuilder;
import cache.CacheBuilder.CacheEngineer.CacheEngineer;
import cache.exceptions.NullElementException;

public class Test
{
    public static void main(String[] args) throws NullElementException
    {
        CacheBuilder<Integer, Integer> builder = new DiskCacheBuilder<>();
        CacheEngineer<Integer, Integer> cacheEngineer = new CacheEngineer<>(builder);
        Cache<Integer, Integer> cache = cacheEngineer.manufactureCache(3);
        cache.putKeyAndValue(1, 1);
        cache.putKeyAndValue(2, 2);
        cache.putKeyAndValue(3, 3);
        System.out.println(cache.getValueByKey(4));
    }
}
