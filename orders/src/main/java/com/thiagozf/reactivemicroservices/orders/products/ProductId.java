package com.thiagozf.reactivemicroservices.orders.products;

import com.thiagozf.reactivemicroservices.orders.domain.AggregateId;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class ProductId implements AggregateId {

    private String value;

    @ConstructorProperties({ "value" })
    public ProductId(String value) {
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
        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}