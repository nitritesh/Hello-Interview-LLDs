package com.lld.ratelimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sliding window log limiter: keeps the timestamps of recent requests per
 * client and allows a new one only if fewer than {limit} fall inside the
 * window. Accurate, at the cost of storing timestamps.
 */
public class SlidingWindowRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowMs;
    private final Map<String, Deque<Long>> requests = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int limit, long windowMs) {
        this.limit = limit;
        this.windowMs = windowMs;
    }

    @Override
    public boolean allow(String clientId) {
        long now = System.currentTimeMillis();
        Deque<Long> timestamps = requests.computeIfAbsent(clientId, k -> new ArrayDeque<>());
        synchronized (timestamps) {
            // Drop timestamps that have slid out of the window.
            while (!timestamps.isEmpty() && now - timestamps.peekFirst() >= windowMs) {
                timestamps.pollFirst();
            }
            if (timestamps.size() < limit) {
                timestamps.addLast(now);
                return true;
            }
            return false;
        }
    }
}
