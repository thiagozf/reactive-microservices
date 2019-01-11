package com.thiagozf.reactivemicroservices.orders.products;

import com.thiagozf.reactivemicroservices.orders.domain.Aggregate;
import com.thiagozf.reactivemicroservices.orders.products.events.domain.ProductCreated;

import java.math.BigDecimal;

public class Product implements Aggregate<ProductId> {
    private ProductId id;
    private String description;
    private BigDecimal price;
    private Status status;
    private Long createdAt;
    private Long updatedAt;

    Product() {
    }

    public Product(ProductCreated event) {
        this.id = event.getAggregateId();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.createdAt = event.getTimestamp();
        this.status = Status.AVAILABLE;
    }

    public ProductId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    public enum Status {
        AVAILABLE
    }
}