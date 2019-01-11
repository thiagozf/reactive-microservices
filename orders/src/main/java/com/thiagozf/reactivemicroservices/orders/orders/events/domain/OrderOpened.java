package com.thiagozf.reactivemicroservices.orders.orders.events.domain;

import com.thiagozf.reactivemicroservices.orders.customer.CustomerId;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class OrderOpened extends OrderEvent {

    private CustomerId customerId;

    @ConstructorProperties({ "aggregateId", "customerId" })
    public OrderOpened(final OrderId aggregateId, final CustomerId customerId) {
        super(aggregateId);
        this.customerId = customerId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderOpened that = (OrderOpened) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerId);
    }
}
