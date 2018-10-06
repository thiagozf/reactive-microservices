package com.thiagozf.reactivemicroservices.products.products.api;

import com.thiagozf.reactivemicroservices.products.products.commands.control.ProductCommandService;
import com.thiagozf.reactivemicroservices.products.products.commands.domain.*;
import com.thiagozf.reactivemicroservices.products.products.Product;
import com.thiagozf.reactivemicroservices.products.products.ProductId;
import com.thiagozf.reactivemicroservices.products.products.events.domain.ProductCreated;
import com.thiagozf.reactivemicroservices.products.products.queries.ProductQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@ExposesResourceFor(Product.class)
@RequestMapping("/products")
public class ProductsResource {

    private static final Logger log = LoggerFactory.getLogger(ProductsResource.class);

    private final ProductCommandService commandService;
    private final ProductQueryService queryService;

    @Autowired
    public ProductsResource(final ProductCommandService commandService, final ProductQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity createProduct(@NotNull @Valid @RequestBody CreateProductRequest createProductRequest) throws URISyntaxException {
        log.debug("REST request to create product : {}", createProductRequest);
        final ProductId productId = new ProductId(createProductRequest.getId());
        final String description = createProductRequest.getDescription();
        final BigDecimal price = createProductRequest.getPrice();

        final ProductCreated productCreated = commandService.createProduct(new CreateProductCommand(productId, description, price));

        final URI uri = new URI("/products/" + productId.getValue());
        return ResponseEntity.created(uri).body(productCreated);
    }

    @GetMapping
    public ResponseEntity listProducts() {
        return ResponseEntity.ok(queryService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProduct(@NotNull @PathVariable String productId) {
        log.debug("REST request get product : {}", productId);
        return queryService.getProduct(new ProductId(productId))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
