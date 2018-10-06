package com.thiagozf.reactivemicroservices.products.products.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProductRequest {
    @NotNull
    @Size(min = 3)
    private String id;

    @NotNull
    @Size(min = 3)
    private String description;

    @NotNull
    @Min(0)
    private BigDecimal price;

    public CreateProductRequest() {
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
