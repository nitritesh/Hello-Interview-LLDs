package com.lld.movieticket;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/** A screening of a movie on a screen at a time, tracking per-seat status. */
public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final Map<String, SeatStatus> seatStatus = new LinkedHashMap<>();

    public Show(String id, Movie movie, Screen screen, LocalDateTime startTime) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        for (Seat seat : screen.getSeats()) {
            seatStatus.put(seat.getId(), SeatStatus.AVAILABLE);
        }
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Map<String, SeatStatus> getSeatStatus() {
        return seatStatus;
    }

    public boolean isAvailable(String seatId) {
        return seatStatus.get(seatId) == SeatStatus.AVAILABLE;
    }

    public void markBooked(String seatId) {
        seatStatus.put(seatId, SeatStatus.BOOKED);
    }
}
