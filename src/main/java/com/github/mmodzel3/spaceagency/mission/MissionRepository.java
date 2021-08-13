package com.github.mmodzel3.spaceagency.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface MissionRepository extends JpaRepository<Mission, String> {
}
