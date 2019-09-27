package com.thiagozf.reactivemicroservices.orders.orders;

import com.thiagozf.reactivemicroservices.orders.customer.CustomerId;
import com.thiagozf.reactivemicroservices.orders.domain.Aggregate;
import com.thiagozf.reactivemicroservices.orders.orders.commands.domain.*;
import com.thiagozf.reactivemicroservices.orders.orders.events.domain.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order implements Aggregate<OrderId> {
    private OrderId id;
    private CustomerId customerId;
    private List<OrderItem> lines = new ArrayList<>();
    private Status status;
    private Long createdAt;
    private Long updatedAt;

    public static OrderOpened open(OpenOrderCommand command) {
        return new OrderOpened(command.getOrderId(), command.getCustomerId());
    }

    Order() {
    }

    public Order(OrderOpened event) {
        this.id = event.getAggregateId();
        this.customerId = event.getCustomerId();
        this.status = Status.OPEN;
        this.createdAt = event.getTimestamp();
    }

    public OrderPlaced on(PlaceOrderCommand command) {
        this.checkStatus();
        return new OrderPlaced(command.getOrderId());
    }

    public OrderCancelled on(CancelOrderCommand command) {
        this.checkStatus();
        return new OrderCancelled(command.getOrderId());
    }

    public ItemAdded on(AddItemCommand command) {
        this.checkStatus();
        return new ItemAdded(command.getOrderId(), command.getProductId(), command.getPrice(), command.getQuantity());
    }

    public ItemRemoved on(RemoveItemCommand command) {
        this.checkStatus();
        return new ItemRemoved(command.getOrderId(), command.getProductId());
    }

    public Order removeItem(ItemRemoved event) {
        this.lines.removeIf(item -> event.getProductId().equals(item.getProductId()));
        this.updatedAt = event.getTimestamp();
        return this;
    }

    public Order addItem(ItemAdded event) {
        this.lines.add(new OrderItem(event.getProductId(), event.getPrice(), event.getQuantity()));
        this.updatedAt = event.getTimestamp();
        return this;
    }

    public Order cancel(OrderCancelled event) {
        this.status = Status.CANCELLED;
        this.updatedAt = event.getTimestamp();
        return this;
    }

    public Order place(OrderPlaced event) {
        this.status = Status.PLACED;
        this.updatedAt = event.getTimestamp();
        return this;
    }

    @Override
    public OrderId getId() {
        return id;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public Status getStatus() {
        return this.status;
    }

    public BigDecimal getTotal() {
        return lines.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderItem> getLines() {
        return lines;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    private void checkStatus() {
        Assert.state(this.status == Status.OPEN, "Order is not open");
    }

    public enum Status {
        OPEN,
        PLACED,
        CANCELLED
    }
}
