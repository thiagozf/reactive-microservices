package com.thiagozf.reactivemicroservices.products.products.events.domain;

import com.thiagozf.reactivemicroservices.products.domain.Event;
import com.thiagozf.reactivemicroservices.products.products.ProductId;

import java.time.Instant;

public abstract class ProductEvent extends Event<ProductId> {
    ProductEvent(final ProductId aggregateId) {
        super(aggregateId, Instant.now().toEpochMilli());
    }

    ProductEvent(final ProductId aggregateId, final Long timestamp) {
        super(aggregateId, timestamp);
    }
}
