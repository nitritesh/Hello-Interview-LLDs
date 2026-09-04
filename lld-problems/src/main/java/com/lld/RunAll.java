package com.lld;

/**
 * Convenience entry point that runs every problem's demo in sequence.
 * In IntelliJ you can also run each package's *Demo class individually.
 */
public class RunAll {
    public static void main(String[] args) throws Exception {
        run("Connect Four", com.lld.connectfour.ConnectFourDemo.class);
        run("Amazon Locker", com.lld.amazonlocker.AmazonLockerDemo.class);
        run("Elevator", com.lld.elevator.ElevatorDemo.class);
        run("Parking Lot", com.lld.parkinglot.ParkingLotDemo.class);
        run("File System", com.lld.filesystem.FileSystemDemo.class);
        run("Movie Ticket Booking", com.lld.movieticket.MovieTicketDemo.class);
        run("Logging Service", com.lld.logging.LoggingDemo.class);
        run("Rate Limiter", com.lld.ratelimiter.RateLimiterDemo.class);
        run("Inventory Management", com.lld.inventory.InventoryDemo.class);
    }

    private static void run(String title, Class<?> demo) throws Exception {
        System.out.println("\n==================================================");
        System.out.println("  " + title);
        System.out.println("==================================================");
        demo.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
    }
}
