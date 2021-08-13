package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.product.ProductNotFoundException;
import com.github.mmodzel3.spaceagency.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
class OrderController {
    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/order")
    void makeOrder(@AuthenticationPrincipal User user, @RequestBody Set<Long> productIds) throws ProductNotFoundException, NoProductsException {
        orderService.makeOrder(user, productIds);
    }
}
