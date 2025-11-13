package com.GoodDemo.service.impl;

import com.GoodDemo.model.Order;
import com.GoodDemo.service.PriceCalculator;

/**
 * Calculadora por defecto:
 * total = subtotal + (subtotal * taxRate de la orden).
 * Mantiene la lógica simple y cohesionada con Order (usa sus getters).
 */
public class DefaultPriceCalculator implements PriceCalculator {
    @Override
    public double computeTotal(Order order) {
        double sub = order.subtotal();
        double tax = sub * order.taxRate();
        return sub + tax;
    }
}
