package com.thiagozf.reactivemicroservices.orders.orders.events.domain;

import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

import java.beans.ConstructorProperties;

public class OrderPlaced extends OrderEvent {

    @ConstructorProperties({ "aggregateId", "timestamp" })
    public OrderPlaced(final OrderId aggregateId, final Long timestamp) {
        super(aggregateId, timestamp);
    }

    public OrderPlaced(final OrderId aggregateId) {
        super(aggregateId);
    }
}
