package com.github.mmodzel3.spaceagency.product;

import org.springframework.web.bind.annotation.*;

@RestController
class ProductManagerController {
    private final ProductService productService;

    ProductManagerController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/admin/products")
    void addProduct(@RequestBody Product product) {
        productService.add(product);
    }

    @DeleteMapping("/api/admin/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.remove(id);
    }
}
