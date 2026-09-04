package com.lld.parkinglot;

/** Spot sizes ordered small to large. */
public enum SpotType {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int rank;

    SpotType(int rank) {
        this.rank = rank;
    }

    /** Which spot a vehicle needs at minimum. */
    public static SpotType requiredFor(VehicleType type) {
        switch (type) {
            case MOTORCYCLE:
                return SMALL;
            case CAR:
                return MEDIUM;
            case TRUCK:
                return LARGE;
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public boolean fits(VehicleType type) {
        return this.rank >= requiredFor(type).rank;
    }
}
