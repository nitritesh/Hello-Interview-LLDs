package com.lld.parkinglot;

import java.time.Instant;

public class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final int floor;
    private final Instant entryTime;

    public Ticket(String id, Vehicle vehicle, ParkingSpot spot, int floor) {
        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;
        this.floor = floor;
        this.entryTime = Instant.now();
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public int getFloor() {
        return floor;
    }

    public Instant getEntryTime() {
        return entryTime;
    }
}
