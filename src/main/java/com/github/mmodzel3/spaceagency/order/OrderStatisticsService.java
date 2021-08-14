package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.mission.Mission;
import com.github.mmodzel3.spaceagency.product.Product;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
class OrderStatisticsService {
    private final OrderRepository orderRepository;

    OrderStatisticsService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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

    Optional<Mission> getMostOrderedMission() {
        List<Order> orders = orderRepository.findAll();

        Map<Mission, Long> missionsCounts = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getMission)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Optional<Map.Entry<Mission, Long>> mostOrderedEntry = missionsCounts.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        return mostOrderedEntry.map(Map.Entry::getKey);
    }
}
