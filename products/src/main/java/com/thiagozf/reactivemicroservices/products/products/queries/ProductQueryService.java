package com.thiagozf.reactivemicroservices.products.products.queries;

import com.thiagozf.reactivemicroservices.products.products.Product;
import com.thiagozf.reactivemicroservices.products.products.ProductId;
import com.thiagozf.reactivemicroservices.products.products.ProductsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductQueryService {

    private final ProductsStorage productsStorage;

    @Autowired
    public ProductQueryService(ProductsStorage productsStorage) {
        this.productsStorage = productsStorage;
    }

    public Optional<Product> getProduct(final ProductId productId) {
        return productsStorage.get(productId);
    }

    public Collection<Product> getProducts() {
        return productsStorage.all();
    }
}

