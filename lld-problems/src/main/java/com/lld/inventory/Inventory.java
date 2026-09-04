package com.lld.inventory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tracks stock per SKU and notifies observers on every change.
 * Uses the observer pattern so alerting/reordering is decoupled from storage.
 */
public class Inventory {
    private final Map<String, InventoryItem> items = new LinkedHashMap<>();
    private final List<StockObserver> observers = new ArrayList<>();

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void addProduct(Product product, int quantity, int reorderThreshold) {
        items.put(product.getSku(), new InventoryItem(product, quantity, reorderThreshold));
    }

    /** Records incoming stock (a delivery). */
    public void restock(String sku, int amount) {
        InventoryItem item = require(sku);
        item.add(amount);
        System.out.println("Restocked " + item.getProduct().getName() + " +" + amount
                + " -> " + item.getQuantity());
        notifyObservers(item);
    }

    /** Records an outgoing sale/shipment. */
    public void sell(String sku, int amount) {
        InventoryItem item = require(sku);
        item.remove(amount);
        System.out.println("Sold " + item.getProduct().getName() + " -" + amount
                + " -> " + item.getQuantity());
        notifyObservers(item);
    }

    public int getQuantity(String sku) {
        return require(sku).getQuantity();
    }

    public void printReport() {
        System.out.println("--- Inventory report ---");
        for (InventoryItem item : items.values()) {
            System.out.println(String.format("  %-12s qty=%-4d %s",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.isLow() ? "(LOW)" : ""));
        }
    }

    private InventoryItem require(String sku) {
        InventoryItem item = items.get(sku);
        if (item == null) {
            throw new IllegalArgumentException("Unknown SKU: " + sku);
        }
        return item;
    }

    private void notifyObservers(InventoryItem item) {
        for (StockObserver observer : observers) {
            observer.onStockChanged(item);
        }
    }
}
