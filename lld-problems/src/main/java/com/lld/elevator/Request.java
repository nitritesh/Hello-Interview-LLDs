package com.lld.elevator;

/** A request to travel to a target floor. */
public class Request {
    private final int targetFloor;

    public Request(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}
