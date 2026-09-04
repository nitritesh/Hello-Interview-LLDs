package com.lld.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Fixed window counter: counts requests per client within a fixed time bucket
 * and resets at each boundary. Simple and cheap, but allows bursts across the
 * boundary (up to 2x the limit).
 */
public class FixedWindowRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowMs;
    private final Map<String, Window> windows = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(int limit, long windowMs) {
        this.limit = limit;
        this.windowMs = windowMs;
    }

    @Override
    public boolean allow(String clientId) {
        long now = System.currentTimeMillis();
        Window window = windows.computeIfAbsent(clientId, k -> new Window());
        synchronized (window) {
            long currentBucket = now / windowMs;
            if (currentBucket != window.bucket) {
                window.bucket = currentBucket;
                window.count = 0;
            }
            if (window.count < limit) {
                window.count++;
                return true;
            }
            return false;
        }
    }

    private static class Window {
        long bucket = -1;
        int count = 0;
    }
}
