package com.thiagozf.reactivemicroservices.orders.orders.events.domain;

import com.thiagozf.reactivemicroservices.orders.orders.OrderId;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class ItemRemoved extends OrderEvent {
    private ProductId productId;

    @ConstructorProperties({ "aggregateId", "productId", "timestamp" })
    public ItemRemoved(OrderId aggregateId, ProductId productId, Long timestamp) {
        super(aggregateId, timestamp);
        this.productId = productId;
    }

    public ItemRemoved(OrderId aggregateId, ProductId productId) {
        super(aggregateId);
        this.productId = productId;
    }

    public ProductId getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemRemoved that = (ItemRemoved) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
