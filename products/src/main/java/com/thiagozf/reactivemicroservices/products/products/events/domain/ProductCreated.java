package com.thiagozf.reactivemicroservices.products.products.events.domain;

import com.thiagozf.reactivemicroservices.products.products.ProductId;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductCreated extends ProductEvent {
    private String description;
    private BigDecimal price;

    @ConstructorProperties({ "aggregateId", "description", "price", "timestamp" })
    public ProductCreated(ProductId aggregateId, String description, BigDecimal price, Long timestamp) {
        super(aggregateId, timestamp);
        this.description = description;
        this.price = price;
    }

    public ProductCreated(ProductId aggregateId, String description, BigDecimal price) {
        super(aggregateId);
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductCreated that = (ProductCreated) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, price);
    }
}
