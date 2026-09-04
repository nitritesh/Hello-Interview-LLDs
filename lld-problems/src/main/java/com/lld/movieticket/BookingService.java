package com.lld.movieticket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Books seats for a show. The reservation is synchronized per show so two
 * concurrent requests can never both grab the same seat (the classic
 * double-booking race).
 */
public class BookingService {

    /** Attempts to book the requested seats atomically. Returns null if any is taken. */
    public Booking book(Show show, List<String> seatIds, String userName) {
        // Lock on the show so the check-and-mark is atomic across threads.
        synchronized (show) {
            for (String seatId : seatIds) {
                if (!show.getSeatStatus().containsKey(seatId)) {
                    System.out.println("No such seat: " + seatId);
                    return null;
                }
                if (!show.isAvailable(seatId)) {
                    System.out.println("Seat " + seatId + " already booked. Booking failed for " + userName);
                    return null;
                }
            }
            // All requested seats are free: commit them.
            for (String seatId : seatIds) {
                show.markBooked(seatId);
            }
            Booking booking = new Booking(UUID.randomUUID().toString().substring(0, 8),
                    show, new ArrayList<>(seatIds), userName);
            System.out.println(userName + " booked seats " + seatIds
                    + " (booking " + booking.getId() + ")");
            return booking;
        }
    }
}
