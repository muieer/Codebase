package org.muieer.java.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.NonNull;

import java.util.concurrent.TimeUnit;

/*
* Caffeine Benchmarks：https://github.com/ben-manes/caffeine/wiki/Benchmarks
* */
public class JvmCacheDemo {

    private JvmCacheDemo() {}

    public static <K, V> Cache<K, V> getCacheInstance(@NonNull Integer duration, @NonNull TimeUnit unit) {
        return Caffeine.newBuilder()
                .expireAfterWrite(duration, unit)
                .initialCapacity(100)
                .maximumSize(1000)
                .build();
    }

    public static void main(String[] args) {

        Cache<Integer, String> cache = getCacheInstance(1, TimeUnit.SECONDS);
        cache.put(1, "a");
        System.out.println(cache.get(1, key -> null));
        System.out.println(cache.get(2, key -> null));

    }

}
