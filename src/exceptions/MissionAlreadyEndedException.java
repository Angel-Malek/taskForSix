package exceptions;

public class MissionAlreadyEndedException extends Exception{

    public MissionAlreadyEndedException(String missionName) {
        super("main.Mission " + missionName + " has already ended");
    }

}
