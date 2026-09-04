package com.lld.amazonlocker;

public class AmazonLockerDemo {
    public static void main(String[] args) {
        LockerLocation location = new LockerLocation("SEATTLE-01");
        location.addLocker(new Locker("L1", LockerSize.SMALL));
        location.addLocker(new Locker("L2", LockerSize.MEDIUM));
        location.addLocker(new Locker("L3", LockerSize.LARGE));

        LockerService service = new LockerService(location);

        Package book = new Package("PKG-BOOK", LockerSize.SMALL);
        String code = service.deposit(book);
        System.out.println("Deposited " + book.getId() + ", pickup code: " + code);

        // A large package: only L3 can hold it.
        Package tv = new Package("PKG-TV", LockerSize.LARGE);
        String code2 = service.deposit(tv);
        System.out.println("Deposited " + tv.getId() + ", pickup code: " + code2);

        // No locker left big enough for another large package.
        Package tv2 = new Package("PKG-TV2", LockerSize.LARGE);
        String code3 = service.deposit(tv2);
        System.out.println("Deposit of " + tv2.getId() + " -> " + (code3 == null ? "NO LOCKER AVAILABLE" : code3));

        // Customer picks up the book, freeing L1.
        Package picked = service.pickup(code);
        System.out.println("Picked up: " + picked.getId());

        // Re-using the same code now fails.
        try {
            service.pickup(code);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}
