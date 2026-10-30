package MarsOpsApp;

abstract public class Emergency {
   
    public enum EmergencyStatus {
        Resolving,
        Resolved,
        UnResolved
    }
    private EmergencyStatus emergencyStatus;
    private String name;
    private int[] location;
    private Faciliity place ;
    private Boolean status;
    private HumanPersonnel personnel;
    private RoboticUnit robot;
    private Resources resources;

    public Emergency(String name, int[] location, Faciliity place) {
        emergencyStatus = EmergencyStatus.UnResolved;
        this.name = name;
        this.location = location;
        this.place = place;
    }

    public int[] getLocation() {
        return location;
    }

    public Faciliity getPlace() {
        return place;
    }

    public Boolean getStatus() {
        return status;
    }
    
    private Boolean assignHuman(HumanPersonnel person){
        if(!person.getavailability())
            return false;
        personnel = person;
        personnel.changeAvailability();
        personnel.setCurrentLocation(location , place);
        return true;
    }
    
    private Boolean assignRobot(RoboticUnit robot){
        if(!robot.getAvailability())
            return false;
        this.robot = robot;
        this.robot.changeAvailability();
        this.robot.setCurrentLocation(location , place);
        return true;
    }
    
    abstract public Boolean resolve();
    abstract public Object[] requirments();
    
    
}

