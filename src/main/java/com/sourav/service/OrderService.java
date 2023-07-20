package com.sourav.service;

import com.sourav.dto.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void initDB() {
        orders.add(new Order(4235, "Mobile", 50000));
        orders.add(new Order(2453, "Book", 600));
        orders.add(new Order(3764, "AC", 45000));
        orders.add(new Order(7568, "Refrigerator", 30000));
    }

    public Order addOrder(Order order) {
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
