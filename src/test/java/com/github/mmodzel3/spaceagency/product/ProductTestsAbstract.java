package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.Mission;
import com.github.mmodzel3.spaceagency.mission.MissionTestsAbstract;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public abstract class ProductTestsAbstract extends MissionTestsAbstract {
    final static int TEST_PRODUCT_PRICE = 200;
    final static double TEST_PRODUCT_X1 = 21.5;
    final static double TEST_PRODUCT_Y1 = 19.5;
    final static double TEST_PRODUCT_X2 = TEST_PRODUCT_X1 + 1.0;
    final static double TEST_PRODUCT_Y2 = TEST_PRODUCT_Y1 + 1.0;
    final static int TEST_PRODUCT_DATE_DAYS_DIFF = 10;
    final static String TEST_PRODUCT_URL = "http://website.com/file.jpg";

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    protected void setUp() {
        productRepository.deleteAll();
        super.setUp();
    }

    Product createTestProduct(Mission mission) {
        return Product.builder()
                .mission(mission)
                .acquisitionDate(LocalDateTime.now().minusDays(TEST_PRODUCT_DATE_DAYS_DIFF))
                .price(TEST_PRODUCT_PRICE)
                .x1(TEST_PRODUCT_X1)
                .y1(TEST_PRODUCT_Y1)
                .x2(TEST_PRODUCT_X2)
                .y2(TEST_PRODUCT_Y2)
                .url(TEST_PRODUCT_URL)
                .build();
    }

    protected Product createAndSaveTestMissionAndProduct() {
        Mission mission = createAndSaveTestMission();
        Product product = createTestProduct(mission);

        productRepository.save(product);

        return product;
    }
}
