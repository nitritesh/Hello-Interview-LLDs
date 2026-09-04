package com.lld.movieticket;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MovieTicketDemo {
    public static void main(String[] args) {
        Movie movie = new Movie("M1", "Inception", 148);
        Screen screen = new Screen("SCR-1", 3, 5); // rows A-C, seats 1-5
        Show show = new Show("SHOW-1", movie, screen, LocalDateTime.now().plusHours(2));

        BookingService service = new BookingService();

        System.out.println("Showing: " + movie.getTitle() + " at " + show.getStartTime());
        System.out.println();

        // Alice books A1, A2.
        service.book(show, Arrays.asList("A1", "A2"), "Alice");

        // Bob tries A2, A3 -> A2 is taken, whole booking fails.
        service.book(show, Arrays.asList("A2", "A3"), "Bob");

        // Bob retries with free seats.
        service.book(show, Arrays.asList("A3", "A4"), "Bob");

        System.out.println("\nSeat map (X = booked):");
        int col = 0;
        StringBuilder sb = new StringBuilder();
        for (String seatId : show.getSeatStatus().keySet()) {
            String mark = show.isAvailable(seatId) ? seatId : "XX";
            sb.append(String.format("%-4s", mark));
            if (++col % 5 == 0) {
                System.out.println(sb.toString());
                sb.setLength(0);
            }
        }
    }
}
