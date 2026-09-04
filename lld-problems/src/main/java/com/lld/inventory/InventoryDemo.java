package com.lld.inventory;

public class InventoryDemo {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addObserver(new LowStockAlert());

        inventory.addProduct(new Product("SKU-1", "Widget", 9.99), 10, 3);
        inventory.addProduct(new Product("SKU-2", "Gadget", 19.99), 5, 2);

        System.out.println();
        inventory.printReport();

        System.out.println("\nProcessing sales...");
        inventory.sell("SKU-1", 8);   // drops to 2 -> low stock alert
        inventory.sell("SKU-2", 2);   // drops to 3

        System.out.println("\nProcessing delivery...");
        inventory.restock("SKU-1", 20);

        System.out.println("\nTrying to oversell...");
        try {
            inventory.sell("SKU-2", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println();
        inventory.printReport();
    }
}
