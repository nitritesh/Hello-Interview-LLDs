package com.lld.inventory;

/** Prints a reorder alert whenever an item drops to or below its threshold. */
public class LowStockAlert implements StockObserver {
    @Override
    public void onStockChanged(InventoryItem item) {
        if (item.isLow()) {
            System.out.println("  [ALERT] Low stock on " + item.getProduct().getName()
                    + " (" + item.getQuantity() + " left, reorder at "
                    + item.getReorderThreshold() + ")");
        }
    }
}
