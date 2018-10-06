package com.thiagozf.reactivemicroservices.orders.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItem {
    private ProductId productId;
    private BigDecimal price;
    private BigDecimal quantity;

    @ConstructorProperties({ "productId", "price", "quantity" })
    public OrderItem(ProductId productId, BigDecimal price, BigDecimal quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductId getProductId() {
        return productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return quantity.multiply(price);
    }
}
