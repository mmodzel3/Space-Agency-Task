package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.product.Product;
import com.github.mmodzel3.spaceagency.product.ProductNotFoundException;
import com.github.mmodzel3.spaceagency.product.ProductService;
import com.github.mmodzel3.spaceagency.user.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    OrderService(OrderRepository orderRepository,
                 ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    void makeOrder(User user, Set<Long> productIds)
            throws ProductNotFoundException, NoProductsException {
        if (productIds.isEmpty()) {
            throw new NoProductsException();
        }

        List<Product> products = productService.findByIds(productIds);

        Order order = Order.builder()
                .customer(user)
                .products(Set.copyOf(products))
                .build();

        orderRepository.save(order);
    }

    List<Order> findCustomerOrders(User user) {
        return orderRepository.findAllByCustomer(user);
    }

    Optional<Product> getMostOrderedProduct() {
        List<Order> orders = orderRepository.findAll();

        Map<Product, Long> productsCounts = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Optional<Map.Entry<Product, Long>> mostOrderedEntry = productsCounts.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        return mostOrderedEntry.map(Map.Entry::getKey);
    }
}
