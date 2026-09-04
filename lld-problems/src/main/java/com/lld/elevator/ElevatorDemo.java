package com.lld.elevator;

public class ElevatorDemo {
    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem();
        system.addElevator(new Elevator(1, 0));
        system.addElevator(new Elevator(2, 9));

        // People press buttons for these floors.
        system.requestFloor(5);   // closest to elevator 1 (floor 0)
        system.requestFloor(8);   // closest to elevator 2 (floor 9)
        system.requestFloor(2);

        System.out.println("\nSimulating...");
        int tick = 0;
        while (system.anyBusy() && tick < 30) {
            tick++;
            system.step();
        }
        System.out.println("\nFinal positions:");
        for (Elevator e : system.getElevators()) {
            System.out.println("Elevator " + e.getId() + " at floor " + e.getCurrentFloor()
                    + " (" + e.getDirection() + ")");
        }
    }
}
