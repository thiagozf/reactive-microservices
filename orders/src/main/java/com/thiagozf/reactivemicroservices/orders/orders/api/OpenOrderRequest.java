package com.thiagozf.reactivemicroservices.orders.orders.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpenOrderRequest {
    @NotNull
    @NotBlank
    private String customerId;

    public String getCustomerId() {
        return this.customerId;
    }
}
