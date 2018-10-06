package com.thiagozf.reactivemicroservices.orders.orders.commands.domain;

import com.thiagozf.reactivemicroservices.orders.domain.Command;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

public class CancelOrderCommand implements Command {
    private final OrderId orderId;

    public CancelOrderCommand(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
