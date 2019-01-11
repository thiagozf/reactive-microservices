package com.thiagozf.reactivemicroservices.orders.orders.events.domain;

import com.thiagozf.reactivemicroservices.orders.domain.Event;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

import java.time.Instant;

public abstract class OrderEvent extends Event<OrderId> {
    OrderEvent(final OrderId aggregateId) {
        super(aggregateId, Instant.now().toEpochMilli());
    }

    OrderEvent(final OrderId aggregateId, final Long timestamp) {
        super(aggregateId, timestamp);
    }
}
