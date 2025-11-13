package com.GoodDemo.service;

import com.GoodDemo.model.Order;

/** Contrato de validación: mantiene bajo acoplamiento al depender de interfaz. */
public interface OrderValidator {
    void validate(Order order);
}