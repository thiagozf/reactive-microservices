package com.thiagozf.reactivemicroservices.products.products.events.control;

import com.thiagozf.reactivemicroservices.products.products.Product;
import com.thiagozf.reactivemicroservices.products.products.ProductsStorage;
import com.thiagozf.reactivemicroservices.products.products.events.domain.ProductCreated;
import com.thiagozf.reactivemicroservices.products.products.events.domain.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ProductEventHandler {

    private static final Logger log = LoggerFactory.getLogger(ProductEventHandler.class);

    private ProductsStorage productsStorage;

    @Autowired
    public ProductEventHandler(ProductsStorage productsStorage) {
        this.productsStorage = productsStorage;
    }

    @SuppressWarnings({"squid:S2201"})
    @KafkaListener(topics = "${app.topics.products}")
    public void listen(@Payload ProductEvent event) {
        log.info("receiving = {}", event);

        if (event.eventType().equals("ProductCreated")) {
            productsStorage.put(new Product((ProductCreated) event));
            return;
        }
    }
}