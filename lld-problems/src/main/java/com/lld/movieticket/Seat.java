package com.lld.movieticket;

public class Seat {
    private final String id; // e.g. "A1"

    public Seat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
