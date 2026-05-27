package main;

public class Rocket {

    private RocketStatus rocketStatus;
    private final String name;

    private String missionName;

    public Rocket( String name) {
        this.name = name;
    }

    public RocketStatus getRocketStatus() {
        return rocketStatus;
    }

    public String getName() {
        return name;
    }

    public String getMissionName() {
        return missionName;
    }

    void assignToMission(String missionName){
        this.missionName = missionName;
        this.rocketStatus = RocketStatus.IN_SPACE;
    }

    void changeStatus (RocketStatus rocketStatus){
        this.rocketStatus = rocketStatus;
    }
}
