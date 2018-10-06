package com.thiagozf.reactivemicroservices.products.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thiagozf.reactivemicroservices.products.domain.Aggregate;
import com.thiagozf.reactivemicroservices.products.products.commands.domain.CreateProductCommand;
import com.thiagozf.reactivemicroservices.products.products.events.domain.ProductCreated;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Aggregate<ProductId> {
    private ProductId id;
    private String description;
    private BigDecimal price;
    private Status status;
    private Long createdAt;
    private Long updatedAt;

    Product() {
    }

    public static ProductCreated create(CreateProductCommand command) {
        return new ProductCreated(command.getProductId(), command.getDescription(), command.getPrice());
    }

    public Product(ProductCreated event) {
        this.id = event.getAggregateId();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.status = Status.AVAILABLE;
        this.createdAt = event.getTimestamp();
    }

    @Override
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