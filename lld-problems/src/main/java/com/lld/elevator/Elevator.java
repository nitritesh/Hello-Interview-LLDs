package com.lld.elevator;

import java.util.TreeSet;

/**
 * A single elevator car using a simplified SCAN (elevator algorithm):
 * it keeps moving in one direction serving requests until none remain
 * that way, then reverses.
 */
public class Elevator {
    private final int id;
    private int currentFloor;
    private Direction direction = Direction.IDLE;

    // Requested floors above and below the current position.
    private final TreeSet<Integer> up = new TreeSet<>();
    private final TreeSet<Integer> down = new TreeSet<>();

    public Elevator(int id, int startFloor) {
        this.id = id;
        this.currentFloor = startFloor;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void addRequest(Request request) {
        int floor = request.getTargetFloor();
        if (floor > currentFloor) {
            up.add(floor);
        } else if (floor < currentFloor) {
            down.add(floor);
        }
        if (direction == Direction.IDLE) {
            direction = floor >= currentFloor ? Direction.UP : Direction.DOWN;
        }
    }

    public boolean hasWork() {
        return !up.isEmpty() || !down.isEmpty();
    }

    /** Advances the elevator by one floor toward its next target. */
    public void step() {
        if (!hasWork()) {
            direction = Direction.IDLE;
            return;
        }
        if (direction == Direction.UP) {
            if (up.isEmpty()) {
                direction = Direction.DOWN;
                step();
                return;
            }
            currentFloor++;
            if (up.contains(currentFloor)) {
                up.remove(currentFloor);
                System.out.println("Elevator " + id + " stopped at floor " + currentFloor);
            }
        } else if (direction == Direction.DOWN) {
            if (down.isEmpty()) {
                direction = Direction.UP;
                step();
                return;
            }
            currentFloor--;
            if (down.contains(currentFloor)) {
                down.remove(currentFloor);
                System.out.println("Elevator " + id + " stopped at floor " + currentFloor);
            }
        }
        if (!hasWork()) {
            direction = Direction.IDLE;
        }
    }

    /** Distance-based cost used by the dispatcher to pick the best car. */
    public int distanceTo(int floor) {
        return Math.abs(currentFloor - floor);
    }
}
