package com.thiagozf.reactivemicroservices.products.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("Product id already exists");
    }
}
