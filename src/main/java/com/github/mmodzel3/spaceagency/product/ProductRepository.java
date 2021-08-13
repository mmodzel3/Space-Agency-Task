package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.MissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByMissionName(String name);
    List<Product> findAllByMissionType(MissionType type);
}
