package com.github.mmodzel3.spaceagency.mission;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.github.mmodzel3.spaceagency.user.UserManagerRequestBuilder.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MissionAdminControllerTests extends MissionTestsAbstract {

    private final static long ZERO_MISSIONS = 0;
    private final static long ONE_MISSION = 1;

    @LocalServerPort
    private int port;

    @Test
    void whenAddMissionThenItIsAdded() {
        Mission mission = createTestMission();

        given().port(port)
                .body(mission)
                .post("/api/admin/missions")
                .then()
                .statusCode(200);

        assertEquals(ONE_MISSION, missionRepository.count());

        Optional<Mission> possibleMission = missionRepository.findById(TEST_MISSION_NAME);
        assertTrue(possibleMission.isPresent());
    }

    @Test
    void whenEditMissionThenItIsUpdated() {
        createAndSaveTestMission();

        Mission editedMission = createTestMission();
        editedMission.setEndDate(LocalDateTime.now().plusDays(1));

        given().port(port)
                .body(editedMission)
                .post("/api/admin/missions/" + TEST_MISSION_NAME)
                .then()
                .statusCode(200);

        Optional<Mission> possibleMission = missionRepository.findById(TEST_MISSION_NAME);
        assertTrue(possibleMission.isPresent());
        assertTrue(possibleMission.get().getEndDate().isAfter(LocalDateTime.now()));
    }

    @Test
    void whenDeleteMissionThenItIsRemoved() {
        createAndSaveTestMission();

        given().port(port)
                .delete("/api/admin/missions/" + TEST_MISSION_NAME)
                .then()
                .statusCode(200);

        assertEquals(ZERO_MISSIONS, missionRepository.count());
    }
}
