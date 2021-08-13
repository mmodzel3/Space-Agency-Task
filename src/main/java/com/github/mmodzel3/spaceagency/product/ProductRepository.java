package com.github.mmodzel3.spaceagency.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByMissionName(String name);
}
