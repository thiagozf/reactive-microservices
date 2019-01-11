package com.thiagozf.reactivemicroservices.orders.products.events.domain;

import com.thiagozf.reactivemicroservices.orders.domain.Event;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;

public abstract class ProductEvent extends Event<ProductId> {
    ProductEvent(final ProductId aggregateId, final Long timestamp) {
        super(aggregateId, timestamp);
    }
}
