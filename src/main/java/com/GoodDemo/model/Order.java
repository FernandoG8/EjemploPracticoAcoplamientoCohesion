package com.GoodDemo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entidad con ALTA COHESIÓN:
 * - Todos los métodos usan los mismos campos (id, customer, items, taxRate).
 * - La validación, el subtotal y el total viven aquí.
 * Esto mejora LCOM* y mantiene el modelo “rico” (no anémico).
 */
public class Order {
    private final String id;
    private final String customer;
    private final double taxRate;   // p.ej. 0.16
    private final List<OrderItem> items = new ArrayList<>();

    public Order(String id, String customer, double taxRate) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id is required");
        if (customer == null || customer.isBlank()) throw new IllegalArgumentException("customer is required");
        if (taxRate < 0) throw new IllegalArgumentException("taxRate must be >= 0");
        this.id = id;
        this.customer = customer;
        this.taxRate = taxRate;
    }

    /** Mantiene la invariantes simples (cohesión: usa estado propio). */
    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("item is required");
        items.add(item);
    }

    /** Subtotal = suma de partidas (usa items). */
    public double subtotal() {
        return items.stream().mapToDouble(OrderItem::lineTotal).sum();
    }

    /** Impuesto calculado en base a taxRate (usa taxRate + subtotal()). */
    public double tax() {
        return subtotal() * taxRate;
    }

    /** Total de la orden (usa subtotal() + tax()). */
    public double total() {
        return subtotal() + tax();
    }

    // Getters sólo de lectura; no exponen detalles mutables
    public String id() { return id; }
    public String customer() { return customer; }
    public double taxRate() { return taxRate; }
    public List<OrderItem> items() { return Collections.unmodifiableList(items); }
}
