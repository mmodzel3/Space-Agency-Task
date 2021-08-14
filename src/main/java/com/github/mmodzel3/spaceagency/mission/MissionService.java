package com.github.mmodzel3.spaceagency.mission;

import org.springframework.stereotype.Service;

@Service
class MissionService {
    private final MissionRepository missionRepository;

    MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    void add(Mission mission) {
        missionRepository.save(mission);
    }

    void update(String name, Mission mission) {
        mission.setName(name);

        missionRepository.save(mission);
    }

    void remove(String name) {
        missionRepository.deleteById(name);
    }
}
