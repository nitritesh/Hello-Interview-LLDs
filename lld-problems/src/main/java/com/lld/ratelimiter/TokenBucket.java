package com.lld.ratelimiter;

/** A single token bucket: tokens refill over time; each request spends one. */
public class TokenBucket {
    private final long capacity;
    private final double refillPerMs;
    private double tokens;
    private long lastRefill;

    public TokenBucket(long capacity, double refillPerSecond) {
        this.capacity = capacity;
        this.refillPerMs = refillPerSecond / 1000.0;
        this.tokens = capacity;
        this.lastRefill = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        refill();
        if (tokens >= 1) {
            tokens -= 1;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        double added = (now - lastRefill) * refillPerMs;
        if (added > 0) {
            tokens = Math.min(capacity, tokens + added);
            lastRefill = now;
        }
    }
}
