package com.lld.movieticket;

import java.util.List;

public class Booking {
    private final String id;
    private final Show show;
    private final List<String> seatIds;
    private final String userName;

    public Booking(String id, Show show, List<String> seatIds, String userName) {
        this.id = id;
        this.show = show;
        this.seatIds = seatIds;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public String getUserName() {
        return userName;
    }
}
