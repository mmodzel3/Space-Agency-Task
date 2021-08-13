package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.MissionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    void add(Product product) {
        productRepository.save(product);
    }

    void remove(Long id) {
        productRepository.deleteById(id);
    }

    List<Product> findByMissionName(String missionName) {
        return productRepository.findAllByMissionName(missionName);
    }

    List<Product> findByMissionType(MissionType type) {
        return productRepository.findAllByMissionType(type);
    }
}
