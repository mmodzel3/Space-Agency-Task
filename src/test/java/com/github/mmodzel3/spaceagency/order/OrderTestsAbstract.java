package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.product.Product;
import com.github.mmodzel3.spaceagency.product.ProductService;
import com.github.mmodzel3.spaceagency.product.ProductTestsAbstract;
import com.github.mmodzel3.spaceagency.user.User;
import com.github.mmodzel3.spaceagency.user.UserService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@SpringBootTest
abstract class OrderTestsAbstract extends ProductTestsAbstract {

    protected final static String CUSTOMER = "customer";
    protected final static String MANAGER = "manager";

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    protected void setUp() {
        orderRepository.deleteAll();

        super.setUp();
    }

    User getActiveUser(String username) {
        return userService.findUser(username).orElseThrow();
    }

    User getActiveUser() {
        return getActiveUser(CUSTOMER);
    }

    Set<Long> createProductsAndGetIds() {
        return Collections.singleton(createAndSaveTestMissionAndProduct().getId());
    }

    Order createOrder(String username, Set<Product> products) {
        return Order.builder()
                .products(products)
                .customer(getActiveUser(username))
                .build();
    }

    Order createAndSaveOrder(String username, Set<Product> products) {
        Order order = createOrder(username, products);

        orderRepository.save(order);

        return order;
    }

    Order createAndSaveOrder(String username) {
        return createAndSaveOrder(username, Collections.singleton(createAndSaveTestMissionAndProduct()));
    }
}
