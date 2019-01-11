package com.thiagozf.reactivemicroservices.products.products.events.control;

import com.thiagozf.reactivemicroservices.products.products.Product;
import com.thiagozf.reactivemicroservices.products.products.ProductsStorage;
import com.thiagozf.reactivemicroservices.products.products.events.domain.ProductCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "${app.topics.products}")
public class ProductEventHandler {

    private ProductsStorage productsStorage;

    @Autowired
    public ProductEventHandler(ProductsStorage productsStorage) {
        this.productsStorage = productsStorage;
    }


    @KafkaHandler
    public void onProductCreated(@Payload ProductCreated productCreated) {
        productsStorage.put(new Product(productCreated));
    }

    @KafkaHandler
    public void onProductDeleted(@Payload(required = false) KafkaNull nul, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) int key) {
        // Do nothing...
    }
}