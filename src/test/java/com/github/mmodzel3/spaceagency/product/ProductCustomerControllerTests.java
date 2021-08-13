package com.github.mmodzel3.spaceagency.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.github.mmodzel3.spaceagency.user.UserCustomerRequestBuilder.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductCustomerControllerTests extends ProductTestsAbstract {
    private final static int ONE_PRODUCT = 1;

    @LocalServerPort
    private int port;

    @Test
    void whenGetProductsByMissionNameThenGotData() {
        createAndSaveTestMissionAndProduct();

        Product[] products = given().port(port)
                .get("/api/mission/" + TEST_MISSION_NAME + "/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ONE_PRODUCT, products.length);
    }

    @Test
    void whenGetProductsByMissionTypeThenGotData() {
        createAndSaveTestMissionAndProduct();

        Product[] products = given().port(port)
                .get("/api/mission/type/" + TEST_MISSION_TYPE + "/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ONE_PRODUCT, products.length);
    }
}
