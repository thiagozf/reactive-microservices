package com.thiagozf.reactivemicroservices.orders.orders.events.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

import java.beans.ConstructorProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCancelled extends OrderEvent {
    @ConstructorProperties({ "orderId", "timestamp" })
    public OrderCancelled(final OrderId aggregateId, final Long timestamp) {
        super(aggregateId, timestamp);
    }

    public OrderCancelled(final OrderId aggregateId) {
        super(aggregateId);
    }
}
