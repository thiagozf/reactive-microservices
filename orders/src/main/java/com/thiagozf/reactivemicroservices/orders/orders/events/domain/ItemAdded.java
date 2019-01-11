package com.thiagozf.reactivemicroservices.orders.orders.events.domain;

import com.thiagozf.reactivemicroservices.orders.orders.OrderId;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.Objects;

public class ItemAdded extends OrderEvent {
    private ProductId productId;
    private BigDecimal price;
    private BigDecimal quantity;

    @ConstructorProperties({ "aggregateId", "productId", "price", "quantity", "timestamp" })
    public ItemAdded(OrderId aggregateId, ProductId productId, BigDecimal price, BigDecimal quantity, Long timestamp) {
        super(aggregateId, timestamp);
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public ItemAdded(OrderId aggregateId, ProductId productId, BigDecimal price, BigDecimal quantity) {
        super(aggregateId);
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemAdded itemAdded = (ItemAdded) o;
        return Objects.equals(productId, itemAdded.productId) &&
                Objects.equals(price, itemAdded.price) &&
                Objects.equals(quantity, itemAdded.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, price, quantity);
    }
}
