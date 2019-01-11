package com.thiagozf.reactivemicroservices.products.products.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

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
