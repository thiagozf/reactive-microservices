package com.thiagozf.reactivemicroservices.orders.orders.events.control;

import com.thiagozf.reactivemicroservices.orders.orders.Order;
import com.thiagozf.reactivemicroservices.orders.orders.OrdersStorage;
import com.thiagozf.reactivemicroservices.orders.orders.events.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "${app.topics.orders}")
public class OrderEventHandler {

    private OrdersStorage ordersStorage;

    @Autowired
    public OrderEventHandler(OrdersStorage ordersStorage) {
        this.ordersStorage = ordersStorage;
    }

    @KafkaHandler
    public void onOrderOpened(OrderOpened event) {
        ordersStorage.put(new Order((OrderOpened) event));
    }

    @KafkaHandler
    public void onOrderPlaced(OrderPlaced orderPlaced) {
        Order order = ordersStorage.get(orderPlaced.getAggregateId());
        order.place(orderPlaced);
    }

    @KafkaHandler
    public void onOrderCancelled(OrderCancelled orderCancelled) {
        Order order = ordersStorage.get(orderCancelled.getAggregateId());
        order.cancel(orderCancelled);
    }

    @KafkaHandler
    public void onItemAdded(ItemAdded itemAdded) {
        Order order = ordersStorage.get(itemAdded.getAggregateId());
        order.addItem(itemAdded);
    }

    @KafkaHandler
    public void onItemRemoved(ItemRemoved itemRemoved) {
        Order order = ordersStorage.get(itemRemoved.getAggregateId());
        order.removeItem(itemRemoved);
    }

    @KafkaHandler
    public void onOrderDeleted(@Payload(required = false) KafkaNull nul, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) int key) {
        // Do nothing...
    }
}