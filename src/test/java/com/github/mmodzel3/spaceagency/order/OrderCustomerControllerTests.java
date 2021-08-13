package com.github.mmodzel3.spaceagency.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Collection;
import java.util.Collections;

import static com.github.mmodzel3.spaceagency.user.UserCustomerRequestBuilder.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
