package exceptions;

public class MissionAlreadyExistsException extends Exception{
    public MissionAlreadyExistsException(String name) {
        super("main.Mission "+ name + " already exists");
    }
}
