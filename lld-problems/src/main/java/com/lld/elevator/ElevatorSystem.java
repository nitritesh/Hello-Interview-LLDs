package com.lld.elevator;

import java.util.ArrayList;
import java.util.List;

/** Manages a bank of elevators and dispatches requests to the nearest car. */
public class ElevatorSystem {
    private final List<Elevator> elevators = new ArrayList<>();

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    /** Assigns a floor request to the closest elevator. */
    public Elevator requestFloor(int floor) {
        Elevator best = null;
        int bestCost = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            int cost = e.distanceTo(floor);
            if (cost < bestCost) {
                bestCost = cost;
                best = e;
            }
        }
        if (best != null) {
            best.addRequest(new Request(floor));
            System.out.println("Dispatched floor " + floor + " to elevator " + best.getId());
        }
        return best;
    }

    public boolean anyBusy() {
        for (Elevator e : elevators) {
            if (e.hasWork()) {
                return true;
            }
        }
        return false;
    }

    /** Advances every elevator by one time-step. */
    public void step() {
        for (Elevator e : elevators) {
            e.step();
        }
    }
}
