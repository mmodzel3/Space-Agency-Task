package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.MissionType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
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

    List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByIds(Set<Long> ids) throws ProductNotFoundException {
        List<Product> products = productRepository.findAllById(ids);

        if (products.size() != ids.size()) {
            throw new ProductNotFoundException();
        }

        return products;
    }

    List<Product> findByMissionName(String missionName) {
        return productRepository.findAllByMissionName(missionName);
    }

    List<Product> findByMissionType(MissionType type) {
        return productRepository.findAllByMissionType(type);
    }

    List<Product> findByDateBefore(LocalDateTime date) {
        return productRepository.findAllByAcquisitionDateBefore(date);
    }

    List<Product> findByDateAfter(LocalDateTime date) {
        return productRepository.findAllByAcquisitionDateAfter(date);
    }

    List<Product> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return productRepository.findAllByAcquisitionDateBetween(startDate, endDate);
    }

    List<Product> findCoveringPoint(double x, double y) {
        return productRepository.findAllByX1LessThanEqualAndY1LessThanEqualAndX2IsGreaterThanEqualAndY2IsGreaterThanEqual(x, y, x, y);
    }
}
