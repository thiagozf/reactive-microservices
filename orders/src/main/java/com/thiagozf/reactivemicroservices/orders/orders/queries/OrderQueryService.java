package com.thiagozf.reactivemicroservices.orders.orders.queries;

import com.thiagozf.reactivemicroservices.orders.orders.Order;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;
import com.thiagozf.reactivemicroservices.orders.orders.OrdersStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderQueryService {

    private final OrdersStorage ordersStorage;

    @Autowired
    public OrderQueryService(OrdersStorage ordersStorage) {
        this.ordersStorage = ordersStorage;
    }

    public Optional<Order> getOrder(final OrderId orderId) {
        return ordersStorage.get(orderId);
    }

    public Collection<Order> getOrders() {
        return ordersStorage.all();
    }
}

