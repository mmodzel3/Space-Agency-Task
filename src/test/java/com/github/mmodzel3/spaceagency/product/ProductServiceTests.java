package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.Mission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceTests extends ProductTestsAbstract {
    private final static long ZERO_PRODUCTS = 0;
    private final static long ONE_PRODUCT = 1;

    @Autowired
    ProductService productService;

    @Test
    void whenAddProductThenItIsAdded() {
        Mission mission = createAndSaveTestMission();
        Product product = createTestProduct(mission);

        productService.add(product);

        assertEquals(ONE_PRODUCT, productRepository.count());
    }

    @Test
    void whenRemoveProductThenItIsDeleted() {
        Product product = createAndSaveTestMissionAndProduct();

        productService.remove(product.getId());

        assertEquals(ZERO_PRODUCTS, productRepository.count());
    }
}
