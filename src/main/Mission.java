package main;

import java.util.ArrayList;
import java.util.List;

public class Mission {

    private List<String> rocketList;
    private String name;
    private MissionStatus missionStatus;

    public Mission(String name) {
        this.name = name;
        this.missionStatus = MissionStatus.SCHEDULED;
        this.rocketList = new ArrayList<>();
    }

    void assignRocket(String rocketName){
       rocketList.add(rocketName);
    }

    public List<String> getRocketList() {
        return rocketList;
    }

    public String getName() {
        return name;
    }

    public MissionStatus getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(MissionStatus missionStatus) {
        this.missionStatus = missionStatus;
    }

    //    void assignRocket(main.Rocket rocker){
//        this.rocketList.add(rocker);
//        missionStatus = rocketListCheck();
//    }
//
//    void removeRocket(main.Rocket rocket){
//        rocketList.remove(rocket);
//        if (rocketList.isEmpty() ){
//           missionStatus = main.MissionStatus.ENDED;
//        }
//    }
//
//   public main.MissionStatus rocketListCheck (){
//        if( rocketList.stream()
//                .anyMatch(rocket -> rocket.getRocketStatus()
//                        .equals(main.RocketStatus.IN_REPAIR))
//        ){
//            missionStatus = main.MissionStatus.PENDING;
//        } else {
//            missionStatus = main.MissionStatus.IN_PROGRESS;
//        }
//        return missionStatus;
//    }
}


