package com.thiagozf.reactivemicroservices.orders.orders.commands.domain;

import com.thiagozf.reactivemicroservices.orders.orders.OrderId;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;

public class RemoveItemCommand {
    private final OrderId orderId;
    private final ProductId productId;

    public RemoveItemCommand(OrderId orderId, ProductId productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public ProductId getProductId() {
        return productId;
    }
}
