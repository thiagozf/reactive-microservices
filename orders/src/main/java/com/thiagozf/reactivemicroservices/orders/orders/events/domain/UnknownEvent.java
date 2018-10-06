package com.thiagozf.reactivemicroservices.orders.orders.events.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

import java.beans.ConstructorProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnknownEvent extends OrderEvent {
    @ConstructorProperties({ "aggregateId", "timestamp" })
    public UnknownEvent(OrderId aggregateId, Long timestamp) {
        super(aggregateId, timestamp);
    }
}