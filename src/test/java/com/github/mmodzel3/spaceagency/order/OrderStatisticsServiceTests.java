package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.mission.Mission;
import com.github.mmodzel3.spaceagency.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderStatisticsServiceTests extends OrderTestsAbstract {

    @Autowired
    OrderStatisticsService orderStatisticsService;

    @Test
    void whenGetMostOrderedProductAndNoProductsThenGotNothing() {
        Optional<Product> possibleMostOrderedProduct = orderStatisticsService.getMostOrderedProduct();

        assertFalse(possibleMostOrderedProduct.isPresent());
    }

    @Test
    void whenGetMostOrderedProductThenGotIt() {
        createAndSaveOrder(MANAGER);

        Order order = createAndSaveOrder(CUSTOMER);
        Product product = order.getProducts().iterator().next();

        createAndSaveOrder(CUSTOMER, Collections.singleton(product));

        Optional<Product> possibleMostOrderedProduct = orderStatisticsService.getMostOrderedProduct();

        assertTrue(possibleMostOrderedProduct.isPresent());
        assertEquals(product.getId(), possibleMostOrderedProduct.get().getId());
    }

    @Test
    void whenGetMostOrderedMissionAndNoProductsThenGotNothing() {
        Optional<Mission> possibleMostOrderedMission = orderStatisticsService.getMostOrderedMission();

        assertFalse(possibleMostOrderedMission.isPresent());
    }

    @Test
    void whenGetMostOrderedMissionThenGotIt() {
        createAndSaveOrder(MANAGER);

        Order order = createAndSaveOrder(CUSTOMER);
        Product product = order.getProducts().iterator().next();
        Mission mission = product.getMission();

        createAndSaveOrder(CUSTOMER, Collections.singleton(product));

        Optional<Mission> possibleMostOrderedMission = orderStatisticsService.getMostOrderedMission();

        assertTrue(possibleMostOrderedMission.isPresent());
        assertEquals(mission.getName(), possibleMostOrderedMission.get().getName());
    }
}
