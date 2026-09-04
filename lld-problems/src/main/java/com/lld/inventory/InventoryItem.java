package com.lld.inventory;

/** A product together with its stock level and reorder threshold. */
public class InventoryItem {
    private final Product product;
    private int quantity;
    private final int reorderThreshold;

    public InventoryItem(Product product, int quantity, int reorderThreshold) {
        this.product = product;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public void add(int amount) {
        quantity += amount;
    }

    public void remove(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough stock of " + product.getName()
                    + " (have " + quantity + ", need " + amount + ")");
        }
        quantity -= amount;
    }

    public boolean isLow() {
        return quantity <= reorderThreshold;
    }
}
