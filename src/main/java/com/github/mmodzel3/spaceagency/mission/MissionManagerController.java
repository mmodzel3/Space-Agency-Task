package com.github.mmodzel3.spaceagency.mission;

import org.springframework.web.bind.annotation.*;

@RestController
public class MissionManagerController {
    private final MissionService missionService;

    public MissionManagerController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping("/api/admin/missions")
    void addMission(@RequestBody Mission mission) {
        missionService.add(mission);
    }

    @PostMapping("/api/admin/missions/{name}")
    void editMission(@PathVariable String name, @RequestBody Mission mission) {
        missionService.update(name, mission);
    }

    @DeleteMapping("/api/admin/missions/{name}")
    void deleteMission(@PathVariable String name) {
        missionService.remove(name);
    }
}
