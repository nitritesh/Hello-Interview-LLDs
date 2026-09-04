package com.lld.movieticket;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String id;
    private final List<Seat> seats = new ArrayList<>();

    public Screen(String id, int rows, int seatsPerRow) {
        this.id = id;
        for (int r = 0; r < rows; r++) {
            char rowLabel = (char) ('A' + r);
            for (int s = 1; s <= seatsPerRow; s++) {
                seats.add(new Seat("" + rowLabel + s));
            }
        }
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
