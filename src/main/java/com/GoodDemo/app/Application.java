package com.GoodDemo.app;

import com.GoodDemo.model.Order;
import com.GoodDemo.model.OrderItem;
import com.GoodDemo.repo.InMemoryOrderRepository;
import com.GoodDemo.repo.OrderRepository;
import com.GoodDemo.service.OrderService;
import com.GoodDemo.service.OrderValidator;
import com.GoodDemo.service.PriceCalculator;
import com.GoodDemo.service.impl.DefaultPriceCalculator;
import com.GoodDemo.service.impl.SimpleOrderValidator;

/** Composition Root: aquí se cablean las dependencias concretas. */
public class Application {

    private final OrderService service;

    public Application() {
        OrderRepository repo = new InMemoryOrderRepository();
        OrderValidator validator = new SimpleOrderValidator();
        PriceCalculator calculator = new DefaultPriceCalculator();
        this.service = new OrderService(repo, validator, calculator);
    }

    public void runDemo() {
        Order o = new Order("P-300", "Firestone", 0.16);
        o.addItem(new OrderItem("Llanta A/T", 2, 1200.0));
        o.addItem(new OrderItem("Válvula", 4, 50.0));

        service.create(o);
        System.out.printf("Subtotal=%.2f  IVA=%.2f  Total=%.2f%n",
                o.subtotal(), o.tax(), o.total());
    }
}
