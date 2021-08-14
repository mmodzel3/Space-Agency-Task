package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.mission.Mission;
import com.github.mmodzel3.spaceagency.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Collections;

import static com.github.mmodzel3.spaceagency.user.UserCustomerRequestBuilder.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderCustomerControllerTests extends OrderTestsAbstract {
    private final static long FAKE_ID = -1;
    private final static int ONE_ORDER = 1;

    private final static int ZERO = 0;
    private final static int ZERO_PRODUCTS = 0;
    private final static int ZERO_MISSIONS = 0;

    @LocalServerPort
    private int port;

    @Test
    void whenGetCustomerOrdersThenGotCorrectData() {
        createAndSaveOrder(CUSTOMER);
        createAndSaveOrder(MANAGER);

        Order[] orders = given().port(port)
                .get("/api/orders")
                .then()
                .statusCode(200)
                .extract()
                .as(Order[].class);

        assertEquals(ONE_ORDER, orders.length);
    }

    @Test
    void whenMakeEmptyOrderThenGotError() {
        given().port(port)
                .body(Collections.EMPTY_SET)
                .post("/api/orders")
                .then()
                .statusCode(500);
    }

    @Test
    void whenMakeOrderWithInvalidProductIdThenGotError() {
        given().port(port)
                .body(Collections.singleton(FAKE_ID))
                .post("/api/orders")
                .then()
                .statusCode(500);
    }

    @Test
    void whenMakeOrderWithCorrectProductItIsAdded() {
        given().port(port)
                .body(createProductsAndGetIds())
                .post("/api/orders")
                .then()
                .statusCode(200);

        assertEquals(ONE_ORDER, orderRepository.count());
    }

    @Test
    void whenGetMostOrderedProductAndNoOrdersThenNothingReturned() {
        Product[] products = given().port(port)
                .get("/api/orders/most/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(ZERO_PRODUCTS, products.length);
    }

    @Test
    void whenGetMostOrderedProductsThenGotCorrectData() {
        Order order = createAndSaveOrder(CUSTOMER);
        Product orderedProduct = order.getProducts().iterator().next();

        Product[] products = given().port(port)
                .get("/api/orders/most/products")
                .then()
                .statusCode(200)
                .extract()
                .as(Product[].class);

        assertEquals(orderedProduct.getId(), products[ZERO].getId());
    }

    @Test
    void whenGetMostOrderedMissionsAndNoOrdersThenNothingReturned() {
        Mission[] missions = given().port(port)
                .get("/api/orders/most/missions")
                .then()
                .statusCode(200)
                .extract()
                .as(Mission[].class);

        assertEquals(ZERO_MISSIONS, missions.length);
    }

    @Test
    void whenGetMostOrderedMissionThenGotCorrectData() {
        Order order = createAndSaveOrder(CUSTOMER);
        Product orderedProduct = order.getProducts().iterator().next();
        Mission orderedMission = orderedProduct.getMission();

        Mission[] missions = given().port(port)
                .get("/api/orders/most/missions")
                .then()
                .statusCode(200)
                .extract()
                .as(Mission[].class);

        assertEquals(orderedMission.getName(), missions[ZERO].getName());
    }
}
