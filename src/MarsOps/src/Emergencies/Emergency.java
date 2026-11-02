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
    private int timer;

    public Emergency(String name, int[] location, Facility place) {
        emergencyStatus = EmergencyStatus.UnResolved;
        this.name = name;
        this.location = location;
        this.place = place;
        this.timer = 0;
    }

    public void increaseTimer(int t){
        this.timer += t;
    }

    public int getTimer() {
        return timer;
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

    public HumanPersonnel getPersonnel() {
        return personnel;
    }

    public RoboticUnit getRobot() {
        return robot;
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
    public Boolean unresolve(){
        System.out.println("emergency is Unresolved...");
        setEmergencyStatus(EmergencyStatus.UnResolved);
        return true;
    }

    abstract public Boolean resolved();

    
    
}

