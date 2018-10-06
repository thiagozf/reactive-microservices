package com.thiagozf.reactivemicroservices.orders.orders.commands.domain;

import com.thiagozf.reactivemicroservices.orders.domain.Command;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

public class AddItemCommand implements Command {
    private OrderId orderId;
    private ProductId productId;
    private BigDecimal price;
    private BigDecimal quantity;

    @ConstructorProperties({ "orderId", "productId", "price", "quantity" })
    public AddItemCommand(OrderId orderId, ProductId productId, BigDecimal price, BigDecimal quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
