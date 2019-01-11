package com.thiagozf.reactivemicroservices.orders.orders.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AddItemRequest {
    @NotNull
    @NotBlank
    private String productId;

    @NotNull
    @Min(1)
    private BigDecimal quantity;

    public String getProductId() {
        return productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
