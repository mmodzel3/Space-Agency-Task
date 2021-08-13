package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.Mission;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.github.mmodzel3.spaceagency.user.UserManagerRequestBuilder.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductAdminControllerTests extends ProductTestsAbstract {
    private final static long ZERO_PRODUCTS = 0;
    private final static long ONE_PRODUCT = 1;

    @LocalServerPort
    private int port;

    @Test
    void whenAddProductThenItIsAdded() {
        Mission mission = createAndSaveTestMission();
        Product product = createTestProduct(mission);

        given().port(port)
                .body(product)
                .post("/api/admin/products/")
                .then()
                .statusCode(200);

        assertEquals(ONE_PRODUCT, productRepository.count());
    }

    @Test
    void whenDeleteProductThenItIsRemoved() {
        Product product = createAndSaveTestMissionAndProduct();

        given().port(port)
                .delete("/api/admin/products/" + product.getId().toString())
                .then()
                .statusCode(200);

        assertEquals(ZERO_PRODUCTS, productRepository.count());
    }
}
