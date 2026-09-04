package com.lld.amazonlocker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Assigns packages to lockers and hands out a one-time pickup code.
 * The customer redeems the code to retrieve the package and free the locker.
 */
public class LockerService {
    private final LockerLocation location;
    private final Map<String, Locker> codeToLocker = new HashMap<>();

    public LockerService(LockerLocation location) {
        this.location = location;
    }

    /** Deposits a package and returns the pickup code, or null if no locker fits. */
    public String deposit(Package pkg) {
        Locker locker = location.findLockerFor(pkg);
        if (locker == null) {
            return null;
        }
        locker.assign(pkg);
        String code = generateCode();
        codeToLocker.put(code, locker);
        return code;
    }

    /** Redeems a pickup code, returning the package and freeing the locker. */
    public Package pickup(String code) {
        Locker locker = codeToLocker.remove(code);
        if (locker == null) {
            throw new IllegalArgumentException("Invalid or already-used code: " + code);
        }
        return locker.release();
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
