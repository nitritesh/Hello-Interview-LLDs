package com.lld.inventory;

/** Observer notified when an item's stock changes. */
public interface StockObserver {
    void onStockChanged(InventoryItem item);
}
