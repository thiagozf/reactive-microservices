package com.thiagozf.reactivemicroservices.orders.orders.commands.domain;

import com.thiagozf.reactivemicroservices.orders.domain.Command;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

public class PlaceOrderCommand implements Command {
    private final OrderId orderId;

    public PlaceOrderCommand(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
