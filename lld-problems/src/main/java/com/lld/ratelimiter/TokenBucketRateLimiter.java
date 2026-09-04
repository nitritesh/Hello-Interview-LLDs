package com.lld.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Per-client token bucket limiter. Allows short bursts up to capacity,
 * then throttles to the sustained refill rate.
 */
public class TokenBucketRateLimiter implements RateLimiter {
    private final ConcurrentHashMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();
    private final long capacity;
    private final double refillPerSecond;

    public TokenBucketRateLimiter(long capacity, double refillPerSecond) {
        this.capacity = capacity;
        this.refillPerSecond = refillPerSecond;
    }

    @Override
    public boolean allow(String clientId) {
        return buckets
                .computeIfAbsent(clientId, k -> new TokenBucket(capacity, refillPerSecond))
                .tryAcquire();
    }
}
