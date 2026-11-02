package Agents;

import Emergencies.*;
import FacilitiesAndResources.*;
import Missions.*;

public abstract class RoboticUnit {

    private String name;
    private int batteryLevel;
    private boolean healthStatus;
    private Facility currentLocation;
    private boolean availability;
    private String typeRobot;
    private int [] location = new int[2];
    public RoboticUnit(String name, int batteryLevel, Facility currentLocation, String typeRobot,int []location) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Battery must be between 0 and 100.");
        }
        this.name = name.trim();
        this.batteryLevel = batteryLevel;
        this.currentLocation = currentLocation;
        this.healthStatus = true;
        this.availability = true;
        this.location=location;
    }

    public String getName() { return name; }
    public int getBatteryLevel() { return batteryLevel; }
    public boolean getHealthStatus() { return healthStatus; }
    public Facility getCurrentLocation() { return currentLocation; }
    public boolean getAvailability() { return availability; }

    public boolean isOperational() {
        return healthStatus && availability && batteryLevel >= 10;
    }

    public boolean chargeBattery(int amount) {
        if (amount <= 0 || batteryLevel == 100) return false;
        batteryLevel = Math.min(100, batteryLevel + amount);
        return true;
    }

    public boolean consumeBattery(int amount) {
        if (amount <= 0 || amount > batteryLevel) return false;
        batteryLevel -= amount;
        return true;
    }

    public boolean changeAvailability() {
        if (!healthStatus) return false;
        availability = !availability;
        return true;
    }

    public String getTypeRobot() {
        return typeRobot;
    }

    public boolean isHealthStatus() {
        return healthStatus;
    }

    public boolean isAvailability() {
        return availability;
    }

    public int[] getLocation() {
        return location;
    }

    public boolean changeHealthStatus() {
        healthStatus = !healthStatus;
        if (!healthStatus) availability = false;
        return healthStatus;
    }

    public boolean setCurrentLocation(Facility location) {
        currentLocation = location;
        return true;
    }

    public boolean repair() {
        if (healthStatus) return false;
        healthStatus = true;
        return true;
    }

    public boolean canPerform(Mission mission) {
        String s=mission.getName();
        if(!getAvailability()||!getHealthStatus())
            return false;
        if(s.equals("repair")){
            if(this.getTypeRobot().equals("repairrobot"))
                return true;
            return false;
        }
        else if (s.equals("research")){
            if(this.getTypeRobot().equals("repairrobot"))
                return true;
            return false;
        }
        else if (s.equals("exploration")){
            if(this.getTypeRobot().equals("rover"))
                return true;
            return false;
        }
        else{
            if(this.getTypeRobot().equals("rover"))
                return true;
            return false;
        }
    }

    public boolean canResolve(Emergency emergency) {
        return emergency != null && isOperational() && hasSkill(emergency.requirments());
    }

    public String toString() {
        return name + " [battery=" + batteryLevel + ", healthy=" + healthStatus + ", available=" + availability + "]";
    }

    public boolean performAction(int cost, String message) {
        if (!isOperational() || !consumeBattery(cost)) return false;
        System.out.println(name + ": " + message);
        return true;
    }
}