public class Rocket {

    private final static String ROCKET_STATUS_ON_GROUND = "On ground";
    private final static String ROCKET_STATUS_IN_SPACE = "In space";
    private final static String ROCKET_STATUS_IN_REPAIR = "In repair";

    private String status;
    private String name;

    public Rocket(String status, String name) {
        this.status = status;
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
