package com.GoodDemo.model;

/**
 * Value Object inmutable: alta cohesión (solo datos + getters).
 * Mantenerlo simple evita acoplamiento.
 */
public final class OrderItem {
    private final String name;
    private final int quantity;
    private final double unitPrice;

    public OrderItem(String name, int quantity, double unitPrice) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
        if (unitPrice < 0) throw new IllegalArgumentException("unitPrice must be >= 0");
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String name() { return name; }
    public int quantity() { return quantity; }
    public double unitPrice() { return unitPrice; }

    /** Costo total de la partida (cohesión dentro del VO). */
    public double lineTotal() { return quantity * unitPrice; }
}
