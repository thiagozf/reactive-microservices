package com.thiagozf.reactivemicroservices.products.products.events.control;

import com.thiagozf.reactivemicroservices.products.products.events.domain.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductEventProducer {

    private static final Logger log = LoggerFactory.getLogger(ProductEventProducer.class);

    private KafkaTemplate<String, ProductEvent> kafkaTemplate;
    private String topic;

    public ProductEventProducer(@Autowired KafkaTemplate<String, ProductEvent> kafkaTemplate,
                                @Value("${app.topics.products}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(ProductEvent... events) {
        for (final ProductEvent event : events) {
            log.info("publishing = {}", event);
            kafkaTemplate.send(topic, event);
        }
    }
}
