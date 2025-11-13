package com.GoodDemo.test;


import com.GoodDemo.model.Order;
import com.GoodDemo.model.OrderItem;
import com.GoodDemo.repo.InMemoryOrderRepository;
import com.GoodDemo.repo.OrderRepository;
import com.GoodDemo.service.OrderService;

import org.junit.jupiter.api.Test;                         // JUnit 5
import static org.junit.jupiter.api.Assertions.*;        // JUnit 5 Assertions
import java.util.List;
/*
public class OrderServiceTest {

    @Test
    public void shouldCreateOrderAndCalculateTotal() {
        OrderRepository repo = new InMemoryOrderRepository();
        OrderValidator validator = new OrderValidator();
        PriceCalculator calculator = new PriceCalculator(0.16);

        OrderService service = new OrderService(repo, validator, calculator);

        Order order = new Order("P-500", "Cliente Demo",
                List.of(new OrderItem("Llanta", 2, 1000.0)));

        service.create(order);

        Order saved = repo.findById("P-500");
        assertNotNull(saved);
        assertEquals(2000.0, saved.getSubtotal(), 0.001);
        assertEquals(320.0, saved.getTax(), 0.001);
        assertEquals(2320.0, saved.getTotal(), 0.001);
    }

    @Test
    public void shouldThrowExceptionForInvalidOrder() {
        OrderRepository repo = new InMemoryOrderRepository();
        OrderValidator validator = new OrderValidator();
        PriceCalculator calculator = new PriceCalculator(0.16);

        OrderService service = new OrderService(repo, validator, calculator);

        Order bad = new Order("X-1", "", List.of(new OrderItem("Producto", 0, 100.0)));
        assertThrows(IllegalArgumentException.class, () -> service.create(bad));
    }


}    */