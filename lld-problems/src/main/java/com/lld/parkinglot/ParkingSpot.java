package com.lld.parkinglot;

public class ParkingSpot {
    private final String id;
    private final SpotType type;
    private Vehicle vehicle;

    public ParkingSpot(String id, SpotType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public SpotType getType() {
        return type;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public boolean canFit(Vehicle v) {
        return isFree() && type.fits(v.getType());
    }

    public void park(Vehicle v) {
        if (!isFree()) {
            throw new IllegalStateException("Spot " + id + " already taken");
        }
        this.vehicle = v;
    }

    public Vehicle remove() {
        Vehicle v = this.vehicle;
        this.vehicle = null;
        return v;
    }
}
