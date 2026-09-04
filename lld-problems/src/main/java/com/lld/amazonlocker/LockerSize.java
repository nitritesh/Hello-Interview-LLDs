package com.lld.amazonlocker;

/** Ordered smallest to largest so a package can fit in any locker >= its size. */
public enum LockerSize {
    SMALL(1),
    MEDIUM(2),
    LARGE(3),
    EXTRA_LARGE(4);

    private final int rank;

    LockerSize(int rank) {
        this.rank = rank;
    }

    public boolean canHold(LockerSize packageSize) {
        return this.rank >= packageSize.rank;
    }
}
