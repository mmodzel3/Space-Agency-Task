package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.product.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTests extends OrderTestsAbstract {
    private final static long FAKE_ID = -1;
    private final static int ONE_ORDER = 1;

    @Autowired
    OrderService orderService;

    @Test
    void whenMakeOrderWithNoProductsThenGotException() {
        assertThrows(NoProductsException.class, () ->
                orderService.makeOrder(getActiveUser(), Collections.EMPTY_SET));
    }

    @Test
    void whenMakeOrderNotExistingProductThenGotException() {
        assertThrows(ProductNotFoundException.class, () ->
                orderService.makeOrder(getActiveUser(), Collections.singleton(FAKE_ID)));
    }

    @Test
    void whenMakeOrderWithCorrectProductsThenItIsAdded() throws ProductNotFoundException, NoProductsException {
        orderService.makeOrder(getActiveUser(), createProductsAndGetIds());

        assertEquals(ONE_ORDER, orderRepository.count());
    }

    @Test
    void whenFindCustomerOrdersThenGotCorrectData() {
        createAndSaveOrder(CUSTOMER);
        createAndSaveOrder(MANAGER);

        List<Order> orders = orderService.findCustomerOrders(getActiveUser(CUSTOMER));

        assertEquals(ONE_ORDER, orders.size());
    }
}
