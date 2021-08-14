package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.mission.Mission;
import com.github.mmodzel3.spaceagency.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
class OrderStatisticsService {
    private final static int STATISTICS_LIMIT = 10;
    private final OrderRepository orderRepository;

    OrderStatisticsService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    List<Product> getMostOrderedProducts() {
        List<Order> orders = orderRepository.findAll();

        Map<Product, Long> productsCounts = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return productsCounts.entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .limit(STATISTICS_LIMIT)
                .collect(Collectors.toList());
    }

    List<Mission> getMostOrderedMissions() {
        List<Order> orders = orderRepository.findAll();

        Map<Mission, Long> missionsCounts = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getMission)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return missionsCounts.entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .limit(STATISTICS_LIMIT)
                .collect(Collectors.toList());
    }
}
