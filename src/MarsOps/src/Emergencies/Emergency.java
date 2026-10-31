package Emergencies;

import FacilitiesAndResources.Facilities;
import Agents.HumanPersonnel;

abstract public class Emergency {
   
    public enum EmergencyStatus {
        Resolving,
        Resolved,
        UnResolved
    }
    private EmergencyStatus emergencyStatus;
    private String name;
    private int[] location;
    private Facilities place ;
    private HumanPersonnel personnel;
    private RoboticUnit robot;
    private Resources resources;

    public Emergency(String name, int[] location, Facilities place) {
        emergencyStatus = EmergencyStatus.UnResolved;
        this.name = name;
        this.location = location;
        this.place = place;
    }

    public int[] getLocation() {
        return location;
    }

    public Facilities getPlace() {
        return place;
    }

    public EmergencyStatus getStatus() {
        return emergencyStatus;
    }

    public void setEmergencyStatus(EmergencyStatus emergencyStatus) {
        this.emergencyStatus = emergencyStatus;
    }
    
    
    protected Boolean assignHuman(HumanPersonnel person){
        if(!person.getavailability())
            return false;
        personnel = person;
        personnel.changeAvailability();
        personnel.setCurrentLocation(location , place);
        return true;
    }
    
    protected Boolean assignRobot(RoboticUnit robot){
        if(!robot.getAvailability())
            return false;
        this.robot = robot;
        this.robot.changeAvailability();
        this.robot.setCurrentLocation(location , place);
        return true;
    }
    
    abstract public Boolean resolve(Object found[]);
    abstract public Object[] requirments();
    
    
}

