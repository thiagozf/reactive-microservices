package com.thiagozf.reactivemicroservices.products.products;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductsStorage {
    private Map<ProductId, Product> products = new ConcurrentHashMap<>();

    public Product put(final Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public Optional<Product> get(final ProductId id) {
        return Optional.ofNullable(products.get(id));
    }

    public Collection<Product> all() {
        return products.values();
    }
}
