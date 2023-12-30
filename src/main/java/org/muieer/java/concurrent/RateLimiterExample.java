package org.muieer.java.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

import java.time.Duration;
import java.time.Instant;

public class RateLimiterExample {

    public static void main(String[] args) {
        usingGoogleGuavaRateLimiter();
        usingResilience4jRateLimiter();
    }

    public static void usingResilience4jRateLimiter() {

        var rateLimiterConfig = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1)) // 一个周期的时间长度
                .limitForPeriod(10) // 每个周期最大可以请求的数量
                .build();

        var rateLimiterRegistry = RateLimiterRegistry.of(rateLimiterConfig);
        var rateLimiter = rateLimiterRegistry.rateLimiter("limiter");

        long start = Instant.now().toEpochMilli();
        for (int i=0; i <= 10; i++) {
            rateLimiter.acquirePermission();
        }
        System.out.println("usingResilience4jRateLimiter cost time is " + (Instant.now().toEpochMilli() - start) + " ms");
    }

    public static void usingGoogleGuavaRateLimiter() {

        var rateLimiter = RateLimiter.create(10);

        long start = Instant.now().toEpochMilli();
        for (int i=0; i <= 10; i++) {
            rateLimiter.acquire();
        }
        System.out.println("usingGoogleGuavaRateLimiter cost time is " + (Instant.now().toEpochMilli() - start) + " ms");
    }
}
