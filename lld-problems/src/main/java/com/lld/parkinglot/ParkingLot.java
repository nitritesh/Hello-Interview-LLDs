package com.lld.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private final String name;
    private final List<ParkingLevel> levels = new ArrayList<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();

    public ParkingLot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addLevel(ParkingLevel level) {
        levels.add(level);
    }

    /** Parks a vehicle on the first level that has a suitable free spot. */
    public Ticket park(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            ParkingSpot spot = level.findSpot(vehicle);
            if (spot != null) {
                spot.park(vehicle);
                Ticket ticket = new Ticket(UUID.randomUUID().toString().substring(0, 8),
                        vehicle, spot, level.getFloor());
                activeTickets.put(ticket.getId(), ticket);
                System.out.println("Parked " + vehicle.getLicensePlate()
                        + " at floor " + level.getFloor() + " spot " + spot.getId()
                        + " (ticket " + ticket.getId() + ")");
                return ticket;
            }
        }
        System.out.println("No spot available for " + vehicle.getLicensePlate());
        return null;
    }

    /** Frees a spot when the vehicle leaves. */
    public void unpark(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Unknown ticket: " + ticketId);
        }
        ticket.getSpot().remove();
        System.out.println("Vehicle " + ticket.getVehicle().getLicensePlate()
                + " left spot " + ticket.getSpot().getId());
    }

    public void printAvailability() {
        for (ParkingLevel level : levels) {
            System.out.println("Floor " + level.getFloor() + ": " + level.freeCount() + " free spots");
        }
    }
}
