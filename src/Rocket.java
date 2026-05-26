public class Rocket {

//    private final static String ROCKET_STATUS_ON_GROUND = "On ground";
//    private final static String ROCKET_STATUS_IN_SPACE = "In space";
//    private final static String ROCKET_STATUS_IN_REPAIR = "In repair";

    private RocketStatus rocketStatus;
    private final String name;

    private String missionName;

    public Rocket(RocketStatus rocketStatus, String name) {
        this.rocketStatus = rocketStatus;
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
