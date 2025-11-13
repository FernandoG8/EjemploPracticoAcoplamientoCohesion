package com.GoodDemo.service.impl;

import com.GoodDemo.model.Order;
import com.GoodDemo.model.OrderItem;
import com.GoodDemo.service.OrderValidator;

/**
 * Validador cohesivo: solo valida reglas sobre el estado de Order.
 * (Se extender con más reglas manteniendo esta clase enfocada.)
 */
public class SimpleOrderValidator implements OrderValidator {
    @Override
    public void validate(Order order) {
        if (order == null) throw new IllegalArgumentException("order is required");
        if (order.customer() == null || order.customer().isBlank())
            throw new IllegalArgumentException("customer is required");
        if (order.items().isEmpty())
            throw new IllegalArgumentException("order must have at least one item");

        for (OrderItem it : order.items()) {
            if (it.quantity() <= 0) throw new IllegalArgumentException("quantity must be > 0");
            if (it.unitPrice() < 0) throw new IllegalArgumentException("unitPrice must be >= 0");
        }
    }
}
