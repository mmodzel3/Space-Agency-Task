package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.product.Product;
import com.github.mmodzel3.spaceagency.product.ProductNotFoundException;
import com.github.mmodzel3.spaceagency.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
class OrderCustomerController {
    private final OrderService orderService;
    private final OrderStatisticsService orderStatisticsService;

    OrderCustomerController(OrderService orderService, OrderStatisticsService orderStatisticsService) {
        this.orderService = orderService;
        this.orderStatisticsService = orderStatisticsService;
    }

    @GetMapping("/api/orders")
    List<Order> getCustomerOrders(@AuthenticationPrincipal User user) {
        return orderService.findCustomerOrders(user);
    }

    @PostMapping("/api/orders")
    void makeOrder(@AuthenticationPrincipal User user, @RequestBody Set<Long> productIds) throws ProductNotFoundException, NoProductsException {
        orderService.makeOrder(user, productIds);
    }

    @GetMapping("/api/orders/most/product")
    Optional<Product> getMostOrderedProduct() {
        return orderStatisticsService.getMostOrderedProduct();
    }

}
