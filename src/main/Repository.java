package main;

import exceptions.*;

import java.util.*;

public class Repository implements RepositoryInterface {
    private final Map<String, Rocket> rockets = new HashMap<>();
    private final Map<String, Mission> missions = new HashMap<>();

    @Override
    public Rocket addRocket(String name) throws RocketAlreadyAssignedExcpeiton {
        String normalizedName = normalizeName(name, "main.Rocket name");
        if (rockets.containsKey(normalizedName)){
            throw new RocketAlreadyAssignedExcpeiton(normalizedName);
        }

        Rocket rocket = new Rocket(normalizedName);
        rockets.put(normalizedName,rocket);
        return rocket;
    }

    @Override
    public Mission addMission(String name) throws MissionAlreadyExistsException {
        String normalizedName = normalizeName(name, "main.Mission name");
        if (missions.containsKey(normalizedName)){
            throw new MissionAlreadyExistsException(normalizedName);
        }

        Mission mission = new Mission(normalizedName);
        missions.put(normalizedName,mission);
        return mission;
    }

    @Override
    public void assignRocketToMission(String rocketName, String missionName) throws RocketNotFoundException, MissionNotFoundException, MissionAlreadyEndedException, RocketAlreadyAssignedExcpeiton {
        Rocket rocket = findRocket(rocketName);
        Mission mission = findMission(missionName);

        if (mission.getMissionStatus() == MissionStatus.ENDED) {
            throw new MissionAlreadyEndedException(mission.getName());
        }
        if (rocket.getMissionName() != null) {
            throw new RocketAlreadyAssignedExcpeiton(rocket.getName(), rocket.getMissionName());
        }

        mission.assignRocket(rocket.getName());
        rocket.assignToMission(mission.getName());
        recalculateMissionStatus(mission);
    }

    @Override
    public void assignRocketsToMission(String missionName, List<String> rocketNames) throws MissionNotFoundException, MissionAlreadyEndedException, RocketNotFoundException, RocketAlreadyAssignedExcpeiton {
        Mission mission = findMission(missionName);
        if (rocketNames == null) {
            throw new IllegalArgumentException("main.Rocket names must not be null");
        }
        if (mission.getMissionStatus() == MissionStatus.ENDED) {
            throw new MissionAlreadyEndedException(mission.getName());
        }

        List<Rocket> rocketsToAssign = new ArrayList<>();
        Set<String> uniqueRocketNames = new HashSet<>();
        for (String rocketName : rocketNames) {
            Rocket rocket = findRocket(rocketName);
            if (!uniqueRocketNames.add(rocket.getName())) {
                throw new IllegalArgumentException("main.Rocket names must be unique");
            }
            if (rocket.getMissionName() != null) {
                throw new RocketAlreadyAssignedExcpeiton(rocket.getName(), rocket.getMissionName());
            }
            rocketsToAssign.add(rocket);
        }

        for (Rocket rocket : rocketsToAssign) {
            rocket.assignToMission(mission.getName());
            mission.assignRocket(rocket.getName());
        }

        recalculateMissionStatus(mission);
    }

    @Override
    public void changeRocketStatus(String rocketName, RocketStatus rocketStatus) throws RocketNotFoundException {
        Rocket rocket = findRocket(rocketName);
        if (rocketStatus == null) {
            throw new IllegalArgumentException("main.Rocket status must not be null");
        }

        String assignedMissionName = rocket.getMissionName();
        if (assignedMissionName == null) {
            if (rocketStatus != RocketStatus.ON_GROUND) {
                throw new IllegalArgumentException("Cannot change an unassigned rocket to " + rocketName);
            }
            rocket.changeStatus(RocketStatus.ON_GROUND);
            return;
        }

        if (rocketStatus == RocketStatus.ON_GROUND) {
            throw new IllegalArgumentException("Cannot change an assigned rocket to ON_GROUND");
        }

        rocket.changeStatus(rocketStatus);
        recalculateMissionStatus(missions.get(assignedMissionName));
    }

    @Override
    public void changeMissionStatus(String missionName, MissionStatus missionStatus) throws MissionNotFoundException {
        Mission mission = findMission(missionName);
        if (missionStatus == null) {
            throw new IllegalArgumentException("main.Mission status must not be null");
        }

        mission.setMissionStatus(MissionStatus.ENDED);
    }

    private static String normalizeName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be null or blank");
        }
        return name.trim();
    }

    private Rocket findRocket(String rocketName) throws RocketNotFoundException {
        String normalizedName = normalizeName(rocketName, "main.Rocket name");
        Rocket rocket = rockets.get(normalizedName);
        if (rocket == null) {
            throw new RocketNotFoundException(normalizedName);
        }
        return rocket;
    }

    private Mission findMission(String missionName) throws MissionNotFoundException {
        String normalizedName = normalizeName(missionName, "main.Mission name");
        Mission mission = missions.get(normalizedName);
        if (mission == null) {
            throw new MissionNotFoundException(normalizedName);
        }
        return mission;
    }

    private void recalculateMissionStatus(Mission mission) {
        if (mission.getMissionStatus() == MissionStatus.ENDED) {
            return;
        }

        List<String> assignedRocketNames = mission.getRocketList();
        if (assignedRocketNames.isEmpty()) {
            mission.setMissionStatus(MissionStatus.SCHEDULED);
            return;
        }

        boolean hasRocketInRepair = assignedRocketNames.stream()
                .map(rockets::get)
                .anyMatch(rocket -> rocket.getRocketStatus() == RocketStatus.IN_REPAIR);

        mission.setMissionStatus(hasRocketInRepair ? MissionStatus.PENDING : MissionStatus.IN_PROGRESS);
    }

}
