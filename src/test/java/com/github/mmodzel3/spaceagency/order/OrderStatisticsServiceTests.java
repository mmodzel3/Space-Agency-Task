package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.mission.Mission;
import com.github.mmodzel3.spaceagency.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderStatisticsServiceTests extends OrderTestsAbstract {
    private final static int ZERO = 0;
    private final static int ZERO_PRODUCTS = 0;
    private final static int ZERO_MISSIONS = 0;

    @Autowired
    OrderStatisticsService orderStatisticsService;

    @Test
    void whenGetMostOrderedProductsAndNoProductsThenGotNothing() {
        List<Product> mostOrderedProducts = orderStatisticsService.getMostOrderedProducts();

        assertEquals(ZERO_PRODUCTS, mostOrderedProducts.size());
    }

    @Test
    void whenGetMostOrderedProductsThenGotThem() {
        createAndSaveOrder(MANAGER);

        Order order = createAndSaveOrder(CUSTOMER);
        Product product = order.getProducts().iterator().next();

        createAndSaveOrder(CUSTOMER, Collections.singleton(product));

        List<Product> mostOrderedProducts = orderStatisticsService.getMostOrderedProducts();

        assertEquals(product.getId(), mostOrderedProducts.get(ZERO).getId());
    }

    @Test
    void whenGetMostOrderedMissionsAndNoProductsThenGotNothing() {
        List<Mission> mostOrderedMissions = orderStatisticsService.getMostOrderedMissions();

        assertEquals(ZERO_MISSIONS, mostOrderedMissions.size());
    }

    @Test
    void whenGetMostOrderedMissionsThenGotThem() {
        createAndSaveOrder(MANAGER);

        Order order = createAndSaveOrder(CUSTOMER);
        Product product = order.getProducts().iterator().next();
        Mission mission = product.getMission();

        createAndSaveOrder(CUSTOMER, Collections.singleton(product));

        List<Mission> mostOrderedMissions = orderStatisticsService.getMostOrderedMissions();

        assertEquals(mission.getName(), mostOrderedMissions.get(ZERO).getName());
    }
}
