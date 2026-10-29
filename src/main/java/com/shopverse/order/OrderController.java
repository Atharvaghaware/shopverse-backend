package com.shopverse.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repo;
    public OrderController(OrderRepository repo) { this.repo = repo; }

    public record OrderItem(@NotNull Long productId, @Min(1) @Max(50) int quantity, @NotBlank String title, @PositiveOrZero double price) {}
    public record CreateOrder(@NotBlank String customerName, @Email @NotBlank String email, @NotBlank String address, @NotEmpty List<@Valid OrderItem> items) {}
    public record OrderResponse(String orderId, String status, double total, String customerName, String email, String address, String itemsJson, String createdAt) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody CreateOrder body) {
        double total = body.items().stream().mapToDouble(i -> i.price() * i.quantity()).sum();
        OrderEntity order = new OrderEntity();
        order.setCustomerName(body.customerName()); order.setEmail(body.email()); order.setAddress(body.address());
        order.setItemsJson(body.items().toString()); order.setTotal(total);
        order = repo.save(order);
        return view(order);
    }

    @GetMapping("/{orderId}")
    public OrderResponse get(@PathVariable String orderId) {
        return repo.findByPublicIdIgnoreCase(orderId).map(this::view)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @GetMapping("/{orderId}/tracking")
    public Map<String,Object> tracking(@PathVariable String orderId) {
        OrderEntity order = repo.findByPublicIdIgnoreCase(orderId)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        List<Map<String,String>> steps = List.of(
          Map.of("status","PLACED","label","Order placed","description","We've received your order."),
          Map.of("status","CONFIRMED","label","Confirmed","description","The order is being prepared."),
          Map.of("status","SHIPPED","label","Shipped","description","Handed to the delivery partner."),
          Map.of("status","OUT_FOR_DELIVERY","label","Out for delivery","description","Your parcel is on its way."),
          Map.of("status","DELIVERED","label","Delivered","description","Order delivered.")
        );
        return Map.of("orderId", order.getPublicId(), "currentStatus", order.getStatus(), "steps", steps,
          "note", "Demo tracking timeline. Live GPS requires a courier integration.");
    }

    private OrderResponse view(OrderEntity o) {
        return new OrderResponse(o.getPublicId(), o.getStatus(), o.getTotal(), o.getCustomerName(), o.getEmail(), o.getAddress(), o.getItemsJson(), o.getCreatedAt().toString());
    }
}
