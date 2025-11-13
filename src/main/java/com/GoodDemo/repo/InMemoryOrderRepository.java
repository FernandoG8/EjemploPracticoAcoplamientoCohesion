package com.GoodDemo.repo;

import com.GoodDemo.model.Order;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementación simple en memoria.
 * Cohesiva (solo responsabilidad de persistencia) y con CBO bajo.
 */
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> db = new ConcurrentHashMap<>();

    @Override public void save(Order order) { db.put(order.id(), order); }

    @Override public Order findById(String id) { return db.get(id); }
}
