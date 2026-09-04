package com.lld.amazonlocker;

import java.util.ArrayList;
import java.util.List;

/** A physical pickup location holding many lockers of varying sizes. */
public class LockerLocation {
    private final String id;
    private final List<Locker> lockers = new ArrayList<>();

    public LockerLocation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addLocker(Locker locker) {
        lockers.add(locker);
    }

    /** Finds the smallest free locker that can hold the package. */
    public Locker findLockerFor(Package pkg) {
        Locker best = null;
        for (Locker locker : lockers) {
            if (locker.canHold(pkg)) {
                if (best == null || locker.getSize().ordinal() < best.getSize().ordinal()) {
                    best = locker;
                }
            }
        }
        return best;
    }
}
