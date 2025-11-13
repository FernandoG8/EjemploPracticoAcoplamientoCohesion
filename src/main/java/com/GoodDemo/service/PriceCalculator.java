package com.GoodDemo.service;

import com.GoodDemo.model.Order;

/** Contrato de cálculo de precios/impuestos: estrategia intercambiable. */
public interface PriceCalculator {
    /** Devuelve el total calculado para la orden. */
    double computeTotal(Order order);
}
