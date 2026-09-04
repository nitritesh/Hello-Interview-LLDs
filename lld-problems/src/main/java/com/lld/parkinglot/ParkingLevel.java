package com.lld.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
    private final int floor;
    private final List<ParkingSpot> spots = new ArrayList<>();

    public ParkingLevel(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    /** Smallest free spot that fits the vehicle, or null. */
    public ParkingSpot findSpot(Vehicle v) {
        ParkingSpot best = null;
        for (ParkingSpot spot : spots) {
            if (spot.canFit(v)) {
                if (best == null || spot.getType().ordinal() < best.getType().ordinal()) {
                    best = spot;
                }
            }
        }
        return best;
    }

    public long freeCount() {
        return spots.stream().filter(ParkingSpot::isFree).count();
    }
}
