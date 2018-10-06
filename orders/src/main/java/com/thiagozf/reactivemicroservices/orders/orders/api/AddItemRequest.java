package com.thiagozf.reactivemicroservices.orders.orders.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddItemRequest {
    @NotNull
    private String productId;

    @NotNull
    @Min(0)
    private BigDecimal quantity;

    public AddItemRequest() {
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
