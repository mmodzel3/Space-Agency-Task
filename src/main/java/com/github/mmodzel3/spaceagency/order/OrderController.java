package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.product.ProductNotFoundException;
import com.github.mmodzel3.spaceagency.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
class OrderController {
    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/api/orders")
    List<Order> getCustomerOrders(@AuthenticationPrincipal User user) {
        return orderService.findCustomerOrders(user);
    }

    @PostMapping("/api/orders")
    void makeOrder(@AuthenticationPrincipal User user, @RequestBody Set<Long> productIds) throws ProductNotFoundException, NoProductsException {
        orderService.makeOrder(user, productIds);
    }
}
