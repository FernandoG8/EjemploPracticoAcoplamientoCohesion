package com.GoodDemo.repo;

import com.GoodDemo.model.Order;

/**
 * Interfaz de puerto (bajo acoplamiento): el servicio depende
 * de una abstracción, no de una implementación concreta.
 */
public interface OrderRepository {
    void save(Order order);
    Order findById(String id);
}
