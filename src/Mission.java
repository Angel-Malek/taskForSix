import java.util.List;

public class Mission {

    private final static String ROCKET_STATUS_ON_GROUND = "On ground";
    private final static String ROCKET_STATUS_IN_SPACE = "In space";
    private final static String ROCKET_STATUS_IN_REPAIR = "In repair";


    private final static String MISSION_STATUS_SCHEDULED = "Scheduled";
    private final static String MISSION_STATUS_PENDING = "Pending";

    private final static String MISSION_STATUS_IN_PROGRESS = "In progress";
    private final static String MISSION_STATUS_ENDED = "Ended";


    private List<Rocket> rocketList;
    private String name;
    private String status;

    public Mission(String name) {
        this.name = name;
        this.status = MISSION_STATUS_SCHEDULED;
    }

    void assignRocket(List<Rocket> rocketList){
        rocketList.addAll(rocketList);
        status = rocketListCheck();
    }

    void assignRocket(Rocket rocker){
        rocketList.add(rocker);
        status = rocketListCheck();
    }

    void removeRocket(Rocket rocket){
        rocketList.remove(rocket);
        if (rocketList.isEmpty() ){
           status = MISSION_STATUS_ENDED;
        }
    }

   public String rocketListCheck (){
        if( rocketList.stream()
                .anyMatch(rocket -> rocket.getStatus()
                        .equals(ROCKET_STATUS_IN_REPAIR))
        ){
            status = MISSION_STATUS_PENDING;
        } else {
            status = MISSION_STATUS_IN_PROGRESS;
        }
        return status;
    }
}


