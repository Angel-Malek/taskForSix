import java.util.List;

public class Mission {

//    private final static String ROCKET_STATUS_ON_GROUND = "On ground";
//    private final static String ROCKET_STATUS_IN_SPACE = "In space";
//    private final static String ROCKET_STATUS_IN_REPAIR = "In repair";


//    private final static String MISSION_STATUS_SCHEDULED = "Scheduled";
//    private final static String MISSION_STATUS_PENDING = "Pending";
//
//    private final static String MISSION_STATUS_IN_PROGRESS = "In progress";
//    private final static String MISSION_STATUS_ENDED = "Ended";


    private List<Rocket> rocketList;
    private String name;
    private MissionStatus missionStatus;

    public Mission(String name) {
        this.name = name;
        this.missionStatus = MissionStatus.SCHEDULED;
    }

    void assignRocket(List<Rocket> rocketList){
        rocketList.addAll(rocketList);
        missionStatus = rocketListCheck();
    }

    void assignRocket(Rocket rocker){
        rocketList.add(rocker);
        missionStatus = rocketListCheck();
    }

    void removeRocket(Rocket rocket){
        rocketList.remove(rocket);
        if (rocketList.isEmpty() ){
           missionStatus = MissionStatus.ENDED;
        }
    }

   public MissionStatus rocketListCheck (){
        if( rocketList.stream()
                .anyMatch(rocket -> rocket.getRocketStatus()
                        .equals(RocketStatus.IN_REPAIR))
        ){
            missionStatus = MissionStatus.PENDING;
        } else {
            missionStatus = MissionStatus.IN_PROGRESS;
        }
        return missionStatus;
    }
}


