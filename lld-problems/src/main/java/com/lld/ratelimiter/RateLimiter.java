package com.lld.ratelimiter;

/** Strategy interface: return true if the request for a client is allowed. */
public interface RateLimiter {
    boolean allow(String clientId);
}
