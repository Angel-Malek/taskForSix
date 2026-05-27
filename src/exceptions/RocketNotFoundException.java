package exceptions;

public class RocketNotFoundException extends Exception {

    public RocketNotFoundException(String rocketName) {
        super("main.Rocket " + rocketName + " was not found");
    }

}
