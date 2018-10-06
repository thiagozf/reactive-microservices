package com.thiagozf.reactivemicroservices.orders.customer;

import com.thiagozf.reactivemicroservices.orders.domain.AggregateId;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class CustomerId implements AggregateId {

    private String value;

    @ConstructorProperties({ "value" })
    public CustomerId(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId customerId = (CustomerId) o;
        return Objects.equals(value, customerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}