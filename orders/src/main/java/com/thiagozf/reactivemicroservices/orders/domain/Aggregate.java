package com.thiagozf.reactivemicroservices.orders.domain;

public interface Aggregate<T extends AggregateId> {
    T getId();
}
