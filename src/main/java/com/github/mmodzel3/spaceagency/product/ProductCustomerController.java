package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.MissionType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ProductCustomerController {
    private final ProductService productService;

    ProductCustomerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/mission/{name}/products")
    List<Product> getProductsByMissionName(@PathVariable String name) {
        return productService.findByMissionName(name);
    }

    @GetMapping("/api/mission/type/{type}/products")
    List<Product> getProductsByMissionType(@PathVariable MissionType type) {
        return productService.findByMissionType(type);
    }
}
