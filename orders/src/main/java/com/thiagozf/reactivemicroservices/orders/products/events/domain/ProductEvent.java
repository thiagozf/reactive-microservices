package com.thiagozf.reactivemicroservices.orders.products.events.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thiagozf.reactivemicroservices.orders.domain.Event;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;

import java.time.Instant;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "eventType",
    defaultImpl = UnknownEvent.class
)
@JsonSubTypes({
    @JsonSubTypes.Type( value = ProductCreated.class, name = "ProductCreated" )
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ProductEvent extends Event<ProductId> {
    ProductEvent(final ProductId aggregateId) {
        super(aggregateId, Instant.now().toEpochMilli());
    }

    ProductEvent(final ProductId aggregateId, final Long timestamp) {
        super(aggregateId, timestamp);
    }
}
