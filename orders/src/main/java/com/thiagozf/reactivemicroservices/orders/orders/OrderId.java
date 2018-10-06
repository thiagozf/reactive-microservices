package com.thiagozf.reactivemicroservices.orders.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thiagozf.reactivemicroservices.orders.domain.AggregateId;

import java.beans.ConstructorProperties;
import java.util.Objects;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderId implements AggregateId {

    private String value;

    @ConstructorProperties({ "value" })
    public OrderId(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static OrderId generate() {
        return new OrderId(UUID.randomUUID().toString());
    }

    public static OrderId of(String value) {
        return new OrderId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return Objects.equals(value, orderId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}