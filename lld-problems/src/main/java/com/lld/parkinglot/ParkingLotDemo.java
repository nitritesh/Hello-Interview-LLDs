package com.lld.parkinglot;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot("Downtown Garage");

        ParkingLevel floor1 = new ParkingLevel(1);
        floor1.addSpot(new ParkingSpot("F1-S1", SpotType.SMALL));
        floor1.addSpot(new ParkingSpot("F1-M1", SpotType.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", SpotType.LARGE));
        lot.addLevel(floor1);

        lot.printAvailability();
        System.out.println();

        Ticket t1 = lot.park(new Motorcycle("MOTO-1"));   // takes small
        Ticket t2 = lot.park(new Car("CAR-1"));           // takes medium
        Ticket t3 = lot.park(new Truck("TRUCK-1"));       // takes large
        lot.park(new Truck("TRUCK-2"));                   // no large spot left

        System.out.println();
        lot.printAvailability();

        System.out.println();
        lot.unpark(t3.getId());                           // frees the large spot
        lot.park(new Truck("TRUCK-2"));                   // now fits

        System.out.println();
        lot.printAvailability();
    }
}
