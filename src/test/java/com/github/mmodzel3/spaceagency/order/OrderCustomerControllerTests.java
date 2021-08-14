package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.mission.Mission;
import com.github.mmodzel3.spaceagency.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Collections;

import static com.github.mmodzel3.spaceagency.user.UserCustomerRequestBuilder.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderCustomerControllerTests extends OrderTestsAbstract {
    private final static long FAKE_ID = -1;
    private final static int ONE_ORDER = 1;

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
        Product product = given().port(port)
                .get("/api/orders/most/product")
                .then()
                .statusCode(200)
                .extract()
                .as(Product.class);

        assertNull(product);
    }

    @Test
    void whenGetMostOrderedProductThenGotCorrectData() {
        Order order = createAndSaveOrder(CUSTOMER);
        Product orderedProduct = order.getProducts().iterator().next();

        Product product = given().port(port)
                .get("/api/orders/most/product")
                .then()
                .statusCode(200)
                .extract()
                .as(Product.class);

        assertEquals(orderedProduct.getId(), product.getId());
    }

    @Test
    void whenGetMostOrderedMissionAndNoOrdersThenNothingReturned() {
        Mission mission = given().port(port)
                .get("/api/orders/most/mission")
                .then()
                .statusCode(200)
                .extract()
                .as(Mission.class);

        assertNull(mission);
    }

    @Test
    void whenGetMostOrderedMissionThenGotCorrectData() {
        Order order = createAndSaveOrder(CUSTOMER);
        Product orderedProduct = order.getProducts().iterator().next();
        Mission orderedMission = orderedProduct.getMission();

        Mission mission = given().port(port)
                .get("/api/orders/most/mission")
                .then()
                .statusCode(200)
                .extract()
                .as(Mission.class);

        assertEquals(orderedMission.getName(), mission.getName());
    }
}
