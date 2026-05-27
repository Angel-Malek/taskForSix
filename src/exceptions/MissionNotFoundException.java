package exceptions;

public class MissionNotFoundException extends Exception{

    public MissionNotFoundException(String missionName) {
        super("main.Rocket " + missionName + " was not found");
    }

}
