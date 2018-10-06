package com.thiagozf.reactivemicroservices.orders.products.events.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;

import java.beans.ConstructorProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnknownEvent extends ProductEvent {
    @ConstructorProperties({ "aggregateId", "timestamp" })
    public UnknownEvent(ProductId aggregateId, Long timestamp) {
        super(aggregateId, timestamp);
    }
}