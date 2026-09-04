package com.lld.ratelimiter;

public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Token bucket: capacity 5, refill 2/sec ===");
        RateLimiter tokenBucket = new TokenBucketRateLimiter(5, 2);
        // Burst of 8 requests: first 5 pass, rest rejected until tokens refill.
        for (int i = 1; i <= 8; i++) {
            System.out.println("Request " + i + " -> " + (tokenBucket.allow("user1") ? "ALLOW" : "REJECT (429)"));
        }
        System.out.println("Waiting 1s for refill...");
        Thread.sleep(1000);
        System.out.println("Request 9 -> " + (tokenBucket.allow("user1") ? "ALLOW" : "REJECT (429)"));

        System.out.println("\n=== Sliding window: 3 requests / 1000ms ===");
        RateLimiter sliding = new SlidingWindowRateLimiter(3, 1000);
        for (int i = 1; i <= 5; i++) {
            System.out.println("Request " + i + " -> " + (sliding.allow("user2") ? "ALLOW" : "REJECT (429)"));
        }

        System.out.println("\n=== Fixed window: 3 requests / 1000ms ===");
        RateLimiter fixed = new FixedWindowRateLimiter(3, 1000);
        for (int i = 1; i <= 4; i++) {
            System.out.println("Request " + i + " -> " + (fixed.allow("user3") ? "ALLOW" : "REJECT (429)"));
        }
    }
}
