package com.thiagozf.reactivemicroservices.orders.orders.commands.control;

import com.thiagozf.reactivemicroservices.orders.orders.Order;
import com.thiagozf.reactivemicroservices.orders.orders.OrderNotFoundException;
import com.thiagozf.reactivemicroservices.orders.orders.OrdersStorage;
import com.thiagozf.reactivemicroservices.orders.orders.events.control.OrderEventProducer;
import com.thiagozf.reactivemicroservices.orders.orders.commands.domain.*;
import com.thiagozf.reactivemicroservices.orders.orders.events.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandService {
    private final OrderEventProducer orderEventProducer;
    private final OrdersStorage ordersStorage;

    @Autowired
    public OrderCommandService(OrderEventProducer orderEventProducer, OrdersStorage ordersStorage) {
        this.orderEventProducer = orderEventProducer;
        this.ordersStorage = ordersStorage;
    }

    public ItemRemoved removeItem(RemoveItemCommand command) {
        final Order order = ordersStorage.get(command.getOrderId()).orElseThrow(OrderNotFoundException::new);
        final ItemRemoved itemRemoved = order.on(command);
        orderEventProducer.publish(itemRemoved);
        return itemRemoved;
    }

    public ItemAdded addItem(AddItemCommand command) {
        final Order order = ordersStorage.get(command.getOrderId()).orElseThrow(OrderNotFoundException::new);
        final ItemAdded itemAdded = order.on(command);
        orderEventProducer.publish(itemAdded);
        return itemAdded;
    }

    public OrderCancelled cancelOrder(CancelOrderCommand command) {
        final Order order = ordersStorage.get(command.getOrderId()).orElseThrow(OrderNotFoundException::new);
        final OrderCancelled orderCancelled = order.on(command);
        orderEventProducer.publish(orderCancelled);
        return orderCancelled;
    }

    public OrderOpened openOrder(OpenOrderCommand command) {
        final OrderOpened orderOpened = Order.open(command);
        orderEventProducer.publish(orderOpened);
        return orderOpened;
    }

    public OrderPlaced placeOrder(PlaceOrderCommand command) {
        final Order order = ordersStorage.get(command.getOrderId()).orElseThrow(OrderNotFoundException::new);
        final OrderPlaced orderPlaced = order.on(command);
        orderEventProducer.publish(orderPlaced);
        return orderPlaced;
    }
}
