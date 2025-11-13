package com.GoodDemo.service;

import com.GoodDemo.model.Order;
import com.GoodDemo.repo.OrderRepository;

/**
 * Servicio con bajo acoplamiento: depende de interfaces (puertos).
 * Orquesta: valida, calcula total y persiste.
 */
public class OrderService {
    private final OrderRepository repo;
    private final OrderValidator validator;
    private final PriceCalculator calculator;

    public OrderService(OrderRepository repo, OrderValidator validator, PriceCalculator calculator) {
        this.repo = repo;
        this.validator = validator;
        this.calculator = calculator;
    }

    public void create(Order order) {
        validator.validate(order);
        // Si se quiere persistir también montos, podriamos extender Order para guardarlos.
        double total = calculator.computeTotal(order);
        // (Aquí sólo demostramos el uso; persistimos la orden tal cual.)
        repo.save(order);
        // Log o side-effect mínimo para demo:
        System.out.printf("[OrderService] total calculado = %.2f%n", total);
    }

    public Order get(String id) {
        return repo.findById(id);
    }
}
