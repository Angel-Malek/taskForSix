package exceptions;

public class RocketAlreadyAssignedExcpeiton extends Exception{
    public RocketAlreadyAssignedExcpeiton(String name) {
        super("Roket "+ name + " already exists");
    }

    public RocketAlreadyAssignedExcpeiton(String name, String missionName) {
        super("Roket "+ name + " already exists "+ "in mission " + missionName);
    }
}
