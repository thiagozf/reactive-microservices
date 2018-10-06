package com.thiagozf.reactivemicroservices.products.products.commands.control;

import com.thiagozf.reactivemicroservices.products.products.Product;
import com.thiagozf.reactivemicroservices.products.products.ProductAlreadyExistsException;
import com.thiagozf.reactivemicroservices.products.products.ProductsStorage;
import com.thiagozf.reactivemicroservices.products.products.commands.domain.*;
import com.thiagozf.reactivemicroservices.products.products.events.control.ProductEventProducer;
import com.thiagozf.reactivemicroservices.products.products.events.domain.ProductCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {
    private final ProductEventProducer productEventproducer;
    private final ProductsStorage productsStorage;

    @Autowired
    public ProductCommandService(ProductEventProducer productEventproducer, ProductsStorage productsStorage) {
        this.productEventproducer = productEventproducer;
        this.productsStorage = productsStorage;
    }


    public ProductCreated createProduct(CreateProductCommand command) {
        // Esta estratégia não é adequada, mas é suficiente para fins de demonstração
        productsStorage.get(command.getProductId()).ifPresent(product -> {
            throw new ProductAlreadyExistsException();
        });

        final ProductCreated productCreated = Product.create(command);
        productEventproducer.publish(productCreated);
        return productCreated;
    }
}
