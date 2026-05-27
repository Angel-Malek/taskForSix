package main;

import exceptions.*;

import java.util.List;

public interface RepositoryInterface {
    Rocket addRocket(String name) throws RocketAlreadyAssignedExcpeiton;

    Mission addMission(String name) throws MissionAlreadyExistsException;

    void assignRocketToMission(String rocketName, String missionName) throws RocketNotFoundException, MissionNotFoundException, MissionAlreadyEndedException, RocketAlreadyAssignedExcpeiton;

    void assignRocketsToMission(String missionName, List<String> rocketNames) throws MissionNotFoundException, MissionAlreadyEndedException, RocketNotFoundException, RocketAlreadyAssignedExcpeiton;

    void changeRocketStatus(String rocketName, RocketStatus rocketStatus) throws RocketNotFoundException;

    void changeMissionStatus(String missionName, MissionStatus missionStatus) throws MissionNotFoundException;
}
