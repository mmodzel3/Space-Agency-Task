package com.github.mmodzel3.spaceagency.mission;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Period;

@SpringBootTest
public abstract class MissionTestsAbstract {
    final static String TEST_MISSION_NAME = "mission";

    @Autowired
    MissionRepository missionRepository;

    @BeforeEach
    protected void setUp() {
        missionRepository.deleteAll();
    }

    Mission createTestMission() {
        return Mission.builder()
                .name(TEST_MISSION_NAME)
                .startDate(LocalDateTime.now().minus(Period.ofDays(10)))
                .endDate(LocalDateTime.now())
                .build();
    }

    protected Mission createAndSaveTestMission() {
        Mission mission = createTestMission();
        missionRepository.save(mission);
        return mission;
    }
}
