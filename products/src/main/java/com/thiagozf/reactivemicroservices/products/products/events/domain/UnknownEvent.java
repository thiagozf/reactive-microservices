package com.thiagozf.reactivemicroservices.products.products.events.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thiagozf.reactivemicroservices.products.products.ProductId;

import java.beans.ConstructorProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnknownEvent extends ProductEvent {
    @ConstructorProperties({ "aggregateId", "timestamp" })
    public UnknownEvent(ProductId aggregateId, Long timestamp) {
        super(aggregateId, timestamp);
    }
}