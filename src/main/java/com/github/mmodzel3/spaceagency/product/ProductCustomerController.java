package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.MissionType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    @GetMapping("/api/products")
    List<Product> getProducts(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        if (startDate != null && endDate != null) {
            return productService.findByDateBetween(startDate, endDate);
        } else if (startDate != null) {
            return productService.findByDateAfter(startDate);
        } else if (endDate != null) {
            return productService.findByDateBefore(endDate);
        } else {
            return productService.findAll();
        }
    }

    @GetMapping("/api/products/covering")
    List<Product> getProductsCoveringPoint(@RequestParam Double latitude,
                              @RequestParam Double longitude) {
        return productService.findCoveringPoint(latitude, longitude);
    }
}
