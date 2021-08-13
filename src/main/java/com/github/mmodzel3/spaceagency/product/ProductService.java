package com.github.mmodzel3.spaceagency.product;

import org.springframework.stereotype.Service;

@Service
class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }
}
