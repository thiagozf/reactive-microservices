package com.thiagozf.reactivemicroservices.orders.orders.commands.domain;

import com.thiagozf.reactivemicroservices.orders.customer.CustomerId;
import com.thiagozf.reactivemicroservices.orders.domain.Command;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

public class OpenOrderCommand implements Command {
    private final OrderId orderId;
    private final CustomerId customerId;

    public OpenOrderCommand(OrderId orderId, CustomerId customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }
}
