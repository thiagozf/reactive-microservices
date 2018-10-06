package com.thiagozf.reactivemicroservices.orders.orders.events.control;

import com.thiagozf.reactivemicroservices.orders.orders.events.domain.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private String topic;

    public OrderEventProducer(@Autowired KafkaTemplate<String, OrderEvent> kafkaTemplate,
                              @Value("${app.topics.orders}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(OrderEvent... events) {
        for (final OrderEvent event : events) {
            log.info("publishing = {}", event);
            kafkaTemplate.send(topic, event);
        }
    }
}
