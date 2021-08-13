package com.github.mmodzel3.spaceagency.mission;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MissionServiceTests extends MissionTestsAbstract {
    private final static long ZERO_MISSIONS = 0;
    private final static long ONE_MISSION = 1;

    @Autowired
    private MissionService missionService;

    @Test
    void whenAddThenMissionIsAdded() {
        Mission mission = createTestMission();

        missionService.add(mission);

        assertEquals(ONE_MISSION, missionRepository.count());
    }

    @Test
    void whenUpdateThenMissionIsUpdated() {
        createAndSaveTestMission();

        Mission mission = createTestMission();
        mission.setStartDate(LocalDateTime.now().plusDays(1));

        missionService.update(TEST_MISSION_NAME, mission);

        Optional<Mission> possibleUpdatedMission = missionRepository.findById(TEST_MISSION_NAME);
        assertTrue(possibleUpdatedMission.isPresent());
        assertTrue(LocalDateTime.now().isBefore(possibleUpdatedMission.get().getStartDate()));
    }

    @Test
    void whenRemoveMissionThenItIsDeleted() {
        createAndSaveTestMission();

        missionService.remove(TEST_MISSION_NAME);

        assertEquals(ZERO_MISSIONS, missionRepository.count());
    }
}
