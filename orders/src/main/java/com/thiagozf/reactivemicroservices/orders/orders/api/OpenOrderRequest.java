package com.thiagozf.reactivemicroservices.orders.orders.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenOrderRequest {
    @NotNull
    private String customerId;

    public OpenOrderRequest() {
    }

    public String getCustomerId() {
        return this.customerId;
    }
}
