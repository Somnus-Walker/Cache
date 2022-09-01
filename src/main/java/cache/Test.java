package cache;

import cache.CacheBuilder.CacheBuilder;
import cache.CacheBuilder.CacheEngineer.CacheEngineer;
import cache.CacheBuilder.builders.DiskCacheBuilder;
import exceptions.NullElementException;

public class Test {

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;

    private Test() {
    }

    public static void main(String[] args) throws NullElementException {
        CacheBuilder<Integer, Integer> builder = new DiskCacheBuilder<>();
        CacheEngineer<Integer, Integer> cacheEngineer = new CacheEngineer<>(builder);
        Cache<Integer, Integer> cache = cacheEngineer.manufactureCache(THREE);
        cache.putKeyAndValue(ONE, ONE);
        cache.putKeyAndValue(TWO, TWO);
        cache.putKeyAndValue(THREE, THREE);
        System.out.println(cache.getValueByKey(FOUR));
    }
}
