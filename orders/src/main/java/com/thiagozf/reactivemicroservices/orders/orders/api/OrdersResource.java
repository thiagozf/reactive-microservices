package com.thiagozf.reactivemicroservices.orders.orders.api;

import com.thiagozf.reactivemicroservices.orders.customer.CustomerId;
import com.thiagozf.reactivemicroservices.orders.orders.Order;
import com.thiagozf.reactivemicroservices.orders.orders.OrderId;
import com.thiagozf.reactivemicroservices.orders.orders.commands.control.OrderCommandService;
import com.thiagozf.reactivemicroservices.orders.orders.commands.domain.*;
import com.thiagozf.reactivemicroservices.orders.orders.events.domain.*;
import com.thiagozf.reactivemicroservices.orders.orders.queries.OrderQueryService;
import com.thiagozf.reactivemicroservices.orders.products.Product;
import com.thiagozf.reactivemicroservices.orders.products.ProductId;
import com.thiagozf.reactivemicroservices.orders.products.ProductNotFoundException;
import com.thiagozf.reactivemicroservices.orders.products.queries.ProductQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@ExposesResourceFor(Order.class)
@RequestMapping("/orders")
public class OrdersResource {

    private static final Logger log = LoggerFactory.getLogger(OrdersResource.class);

    private final OrderCommandService commandService;
    private final OrderQueryService queryService;
    private final ProductQueryService productQueryService;

    @Autowired
    public OrdersResource(final OrderCommandService commandService, final OrderQueryService queryService,
                          final ProductQueryService productQueryService) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.productQueryService = productQueryService;
    }

    @DeleteMapping("/{orderId}/items/{productId}")
    public ResponseEntity removeLine(@PathVariable String orderId, @PathVariable String productId) {
        log.debug("REST request to remove product {} of order {}", productId, orderId);
        final ItemRemoved itemRemoved = commandService.removeItem(new RemoveItemCommand(OrderId.of(orderId), new ProductId(productId)));
        return ResponseEntity.accepted().body(itemRemoved);
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity addItem(@PathVariable String orderId, @NotNull @Valid @RequestBody AddItemRequest addItemRequest) {
        log.debug("REST request to add line to order {} : {}", orderId, addItemRequest);

        final ProductId productId = new ProductId(addItemRequest.getProductId());
        final Product product = productQueryService.getProduct(productId).orElseThrow(ProductNotFoundException::new);

        final ItemAdded itemAdded = commandService.addItem(new AddItemCommand(
                OrderId.of(orderId),
                product.getId(),
                product.getPrice(),
                addItemRequest.getQuantity()
            ));

        return ResponseEntity.accepted().body(itemAdded);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity cancelOrder(@PathVariable String orderId) {
        log.debug("REST request to cancel order : {}", orderId);
        final OrderCancelled orderCancelled = commandService.cancelOrder(new CancelOrderCommand(OrderId.of(orderId)));
        return ResponseEntity.accepted().body(orderCancelled);
    }

    @PostMapping
    public ResponseEntity openOrder(@NotNull @Valid @RequestBody OpenOrderRequest openOrderRequest) throws URISyntaxException {
        log.debug("REST request to open order : {}", openOrderRequest);
        final OrderId orderId = OrderId.generate();
        final CustomerId customerId = new CustomerId(openOrderRequest.getCustomerId());
        final OrderOpened orderOpened = commandService.openOrder(new OpenOrderCommand(orderId, customerId));
        final URI uri = new URI("/orders/" + orderId.getValue());
        return ResponseEntity.created(uri).body(orderOpened);
    }

    @PostMapping("/{orderId}/place")
    public ResponseEntity placeOrder(@PathVariable String orderId) {
        log.debug("REST request to place order : {}", orderId);
        OrderPlaced orderPlaced = commandService.placeOrder(new PlaceOrderCommand(OrderId.of(orderId)));
        return ResponseEntity.accepted().body(orderPlaced);
    }

    @GetMapping
    public ResponseEntity listOrders() {
        return ResponseEntity.ok(queryService.getOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(@NotNull @PathVariable String orderId) {
        log.debug("REST request get order : {}", orderId);
        return queryService.getOrder(OrderId.of(orderId))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
