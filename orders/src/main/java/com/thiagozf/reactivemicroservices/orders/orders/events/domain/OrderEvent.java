package com.thiagozf.reactivemicroservices.orders.orders.events.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thiagozf.reactivemicroservices.orders.domain.Event;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;

import java.time.Instant;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "eventType",
    defaultImpl = UnknownEvent.class
)
@JsonSubTypes({
    @JsonSubTypes.Type( value = OrderOpened.class, name = "OrderOpened" ),
    @JsonSubTypes.Type( value = OrderCancelled.class, name = "OrderCancelled" ),
    @JsonSubTypes.Type( value = OrderPlaced.class, name = "OrderPlaced" ),
    @JsonSubTypes.Type( value = ItemAdded.class, name = "ItemAdded" ),
    @JsonSubTypes.Type( value = ItemRemoved.class, name = "ItemRemoved" )
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class OrderEvent extends Event<OrderId> {
    OrderEvent(final OrderId aggregateId) {
        super(aggregateId, Instant.now().toEpochMilli());
    }

    OrderEvent(final OrderId aggregateId, final Long timestamp) {
        super(aggregateId, timestamp);
    }
}
