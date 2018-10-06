package com.thiagozf.reactivemicroservices.products.products.commands.domain;

import com.thiagozf.reactivemicroservices.products.domain.Command;
import com.thiagozf.reactivemicroservices.products.products.ProductId;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

public class CreateProductCommand implements Command {
    private ProductId productId;
    private String description;
    private BigDecimal price;

    @ConstructorProperties({ "productId", "description", "price" })
    public CreateProductCommand(ProductId productId, String description, BigDecimal price) {
        this.productId = productId;
        this.description = description;
        this.price = price;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
