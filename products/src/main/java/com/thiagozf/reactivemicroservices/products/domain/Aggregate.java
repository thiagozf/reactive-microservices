package com.thiagozf.reactivemicroservices.products.domain;

public interface Aggregate<T extends AggregateId> {
    T getId();
}
