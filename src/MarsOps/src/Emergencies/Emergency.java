package Emergencies;

import Agents.RoboticUnit;
import FacilitiesAndResources.Facility;
import Agents.HumanPersonnel;
import FacilitiesAndResources.Resources;

abstract public class Emergency {
   
    public enum EmergencyStatus {
        Resolving,
        Resolved,
        UnResolved
    }
    private EmergencyStatus emergencyStatus;
    private String name;
    private int[] location;
    private Facility place ;
    private HumanPersonnel personnel;
    private RoboticUnit robot;
    private Resources resources;

    public Emergency(String name, int[] location, Facility place) {
        emergencyStatus = EmergencyStatus.UnResolved;
        this.name = name;
        this.location = location;
        this.place = place;
    }

    public int[] getLocation() {
        return location;
    }

    public Facility getPlace() {
        return place;
    }

    public EmergencyStatus getStatus() {
        return emergencyStatus;
    }

    public void setEmergencyStatus(EmergencyStatus emergencyStatus) {
        this.emergencyStatus = emergencyStatus;
    }
    
    
    protected Boolean assignHuman(HumanPersonnel person){
        if(!person.getAvailability())
            return false;
        personnel = person;
        personnel.changeAvailability();
        personnel.increaseFatigueLevel(5);
        personnel.setCurrentLocation(location , place);
        return true;
    }
    
    protected Boolean assignRobot(RoboticUnit robot){
        if(!robot.getAvailability())
            return false;
        this.robot = robot;
        this.robot.changeAvailability();
        this.robot.consumeBattery(10);
        this.robot.setCurrentLocation(place);
        return true;
    }
    
    abstract public Boolean resolve(Object found[]);
    abstract public Object[] requirments();
    
    
}

