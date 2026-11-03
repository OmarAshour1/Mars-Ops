package Agents;

import FacilitiesAndResources.Facility;
import Missions.Mission;
import Emergencies.*;
import javax.swing.*;

public class HumanPersonnel {
    private String name;
    private int identificationNumber;
    private boolean healthStatus;
    private int[] location;
    private Facility place;
    private String[] skills;
    private int fatigueLevel;
    private boolean availability;
    private String type;

    // Default Constructor
    public HumanPersonnel() {
        this.name = "Unknown";
        this.identificationNumber = 0;
        this.healthStatus = true;
        this.location = new int[]{0, 0};
        this.place = null;
        this.skills = new String[0];
        this.fatigueLevel = 0;
        this.availability = true;
    }

    // Parameterized Constructor
    public HumanPersonnel(String name, int identificationNumber, int[] location, Facility place, String[] skills,String type) {
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.healthStatus = true;
        this.location = location;
        this.place = place;
        this.skills = skills;
        this.fatigueLevel = 0;
        this.availability = true;
        this.type=type;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public boolean getHealthStatus() {
        return healthStatus;
    }

    public int[] getCurrentLocation() {
        return location;
    }

    public Facility getPlace() {
        return place;
    }

    public String[] getSkills() {
        return skills;
    }

    public int getFatigueLevel() {
        return fatigueLevel;
    }

    public boolean getAvailability() {
        return availability;
    }

    public boolean checkSkill(String s){
        for (String x:this.skills){
            if(x.equals(s))
                return true;
        }
        return false;
    }

    public Boolean canPerform(Mission mission) {
            if(!this.getAvailability()||!this.getHealthStatus())
                return false;
            Object [] req=mission.requirments();
            if(req[0]==null)
                return this.checkSkill((String) req[1]);
            return req[0].equals(this.getType())&& this.checkSkill((String) req[1]);
    }

    public Boolean canResolve(Emergency emergency) {
        if(!this.getAvailability()||!this.getHealthStatus())
            return false;
        Object [] req=emergency.requirments();
        return req[0].equals(this.getType())&& this.checkSkill((String) req[1]);
    }

    // Protected setters/modifiers
    public boolean setCurrentLocation(int[] location, Facility place) {
        if (location == null || location.length != 2) {
            return false;
        }
        this.location = location;
        this.place = place; // Allows null for surface of Mars
        return true;
    }

    public boolean decreaseFatigueLevel(int level) {
        if (level < 0) return false;
        this.fatigueLevel = Math.max(0, this.fatigueLevel - level);
        return true;
    }

    public boolean changeHealthStatus(boolean status) {
        this.healthStatus = status;
        return true;
    }

    public boolean isAvailability() {
        return availability;
    }

    public String getType() {
        return type;
    }

    public int[] getLocation() {
        return location;
    }

    public boolean isHealthStatus() {
        return healthStatus;
    }

    public boolean increaseFatigueLevel(int level) {
        if (level < 0) return false;
        this.fatigueLevel = Math.min(5, this.fatigueLevel + level);
        return true;
    }

    public boolean changeAvailability() {
        this.availability = !this.availability;
        return true;
    }
}