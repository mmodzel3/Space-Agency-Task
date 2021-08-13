package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.MissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByMissionName(String name);
    List<Product> findAllByMissionType(MissionType type);
    List<Product> findAllByAcquisitionDateBefore(LocalDateTime date);
    List<Product> findAllByAcquisitionDateAfter(LocalDateTime date);
    List<Product> findAllByAcquisitionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
