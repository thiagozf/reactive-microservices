package com.thiagozf.reactivemicroservices.orders.orders.events.control;

import com.thiagozf.reactivemicroservices.orders.orders.Order;
import com.thiagozf.reactivemicroservices.orders.orders.OrderNotFoundException;
import com.thiagozf.reactivemicroservices.orders.orders.OrdersStorage;
import com.thiagozf.reactivemicroservices.orders.orders.events.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

    private OrdersStorage ordersStorage;

    @Autowired
    public OrderEventHandler(OrdersStorage ordersStorage) {
        this.ordersStorage = ordersStorage;
    }

    @SuppressWarnings({"squid:S2201"})
    @KafkaListener(topics = "${app.topics.orders}")
    public void listen(@Payload OrderEvent event) {
        log.info("receiving = {}", event);

        switch (event.eventType()) {
            case "OrderOpened":
                ordersStorage.put(new Order((OrderOpened) event));
                return;
            case "OrderPlaced":
                ordersStorage.get(event.getAggregateId())
                    .map(order -> order.place((OrderPlaced) event))
                    .orElseThrow(OrderNotFoundException::new);
                return;
            case "OrderCancelled":
                ordersStorage.get(event.getAggregateId())
                        .map(order -> order.cancel((OrderCancelled) event))
                        .orElseThrow(OrderNotFoundException::new);
                return;
            case "ItemAdded":
                ordersStorage.get(event.getAggregateId())
                        .map(order -> order.addItem((ItemAdded) event))
                        .orElseThrow(OrderNotFoundException::new);
                return;
            case "ItemRemoved":
                ordersStorage.get(event.getAggregateId())
                        .map(order -> order.removeItem((ItemRemoved) event))
                        .orElseThrow(OrderNotFoundException::new);
                return;
            default:
        }
    }
}