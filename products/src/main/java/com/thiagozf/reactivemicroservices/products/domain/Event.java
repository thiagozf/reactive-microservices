package com.thiagozf.reactivemicroservices.products.domain;

import java.time.Instant;
import java.util.Objects;

public abstract class Event<T extends AggregateId> {

    private final T aggregateId;
    private final Long timestamp;

    protected Event(final T aggregateId) {
        this(aggregateId, Instant.now().toEpochMilli());
    }

    protected Event(final T aggregateId, final Long timestamp) {
        this.aggregateId = aggregateId;
        this.timestamp = timestamp;
    }

    public T getAggregateId() {
        return aggregateId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String eventType() {
        return this.getClass().getSimpleName();
    }

    public boolean is(Class<? extends Event> cls) {
        return this.getClass().isAssignableFrom(cls);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event that = (Event) o;
        return Objects.equals(aggregateId, that.aggregateId) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId, timestamp);
    }
}