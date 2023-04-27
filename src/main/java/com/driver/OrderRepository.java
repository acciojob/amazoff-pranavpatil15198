package com.driver;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Data
@Repository
public class OrderRepository {
    private HashMap<String, Order> orders = new HashMap<>();

    public Order addOrder(Order order){
        orders.put(order.getId(), order);
        return order;
    }

    public Order getOrderById(String orderId){
        return orders.getOrDefault(orderId, new Order());
    }

    public void deleteOrder(String orderId){
        orders.remove(orderId);
    }
}
