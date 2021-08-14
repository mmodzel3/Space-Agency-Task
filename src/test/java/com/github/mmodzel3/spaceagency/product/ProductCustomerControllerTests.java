package com.github.mmodzel3.spaceagency.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;

import static com.github.mmodzel3.spaceagency.user.UserCustomerRequestBuilder.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductCustomerControllerTests extends ProductTestsAbstract {
    private final static int ZERO_PRODUCTS = 0;
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

    @Test
    void whenGetProductsByDateAfterThenGotData() {
        createAndSaveTestMissionAndProduct();

        Product[] products = given().port(port)
                .param("startDate", LocalDateTime.now().minusDays(TEST_PRODUCT_DATE_DAYS_DIFF + 1).toString())
                .get("/api/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ONE_PRODUCT, products.length);
    }

    @Test
    void whenGetProductsByDateBeforeThenGotData() {
        createAndSaveTestMissionAndProduct();

        Product[] products = given().port(port)
                .param("endDate", LocalDateTime.now().toString())
                .get("/api/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ONE_PRODUCT, products.length);
    }

    @Test
    void whenGetProductsByDateBetweenThenGotData() {
        createAndSaveTestMissionAndProduct();

        Product[] products = given().port(port)
                .param("startDate", LocalDateTime.now().minusDays(TEST_PRODUCT_DATE_DAYS_DIFF + 1).toString())
                .param("endDate", LocalDateTime.now().toString())
                .get("/api/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ONE_PRODUCT, products.length);
    }

    @Test
    void whenGetProductsByDateBetweenNoDataThenGotNoData() {
        createAndSaveTestMissionAndProduct();

        Product[] products = given().port(port)
                .param("startDate", LocalDateTime.now().minusDays(TEST_PRODUCT_DATE_DAYS_DIFF + 2).toString())
                .param("endDate", LocalDateTime.now().minusDays(TEST_PRODUCT_DATE_DAYS_DIFF + 1).toString())
                .get("/api/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ZERO_PRODUCTS, products.length);
    }

    @Test
    void whenGetProductsThenGotData() {
        createAndSaveTestMissionAndProduct();

        Product[] products = given().port(port)
                .get("/api/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ONE_PRODUCT, products.length);
    }

    @Test
    void whenGetProductsCoveringPointThenGotCorrectData() {
        createAndSaveTestMissionAndProduct();

        Product[] products = given().port(port)
                .param("latitude", TEST_PRODUCT_X2)
                .param("longitude", TEST_PRODUCT_Y1)
                .get("/api/products/covering")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ONE_PRODUCT, products.length);
    }
}
