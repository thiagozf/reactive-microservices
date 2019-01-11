package com.thiagozf.reactivemicroservices.orders.orders;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrdersStorage {
    private Map<OrderId, Order> orders = new ConcurrentHashMap<>();

    public Order put(final Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public Order get(final OrderId id) {
        Order order = orders.get(id);
        if (Objects.isNull(order)) {
            throw new OrderNotFoundException();
        }

        return order;
    }

    public Collection<Order> all() {
        return orders.values();
    }
}
