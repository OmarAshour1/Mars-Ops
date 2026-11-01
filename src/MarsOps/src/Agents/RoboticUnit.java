package Agents;

import Emergencies.Emergency;
import FacilitiesAndResources.Facility;
import Missions.Mission;

public class RoboticUnit {

    private int batteryLevel;
    private boolean healthStatus;
    private Facility currentLocation;
    private boolean availability;
    private final String[] skills;

    public RoboticUnit(int batteryLevel, Facility currentLocation, String[] skills) {

        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Battery must be between 0 and 100.");
        }
        if (skills == null || skills.length == 0) {
            throw new IllegalArgumentException("A robot needs at least one skill.");
        }
        String[] copy = new String[skills.length];
        for (int i = 0; i < skills.length; i++) {
            if (skills[i] == null || skills[i].trim().isEmpty()) {
                throw new IllegalArgumentException("Skill names cannot be empty.");
            }
            copy[i] = skills[i].trim().toUpperCase();
        }
        this.batteryLevel = batteryLevel;
        this.currentLocation = currentLocation;
        this.skills = copy;
        this.healthStatus = true;
        this.availability = true;
    }

    // ---------- Getters ----------
    public int getBatteryLevel()          { return batteryLevel; }
    public boolean getHealthStatus()      { return healthStatus; }
    public Facility getCurrentLocation()  { return currentLocation; }
    public boolean getAvailability()      { return availability; }
    public String[] getSkills()           { return skills.clone(); }

    public boolean hasSkill(String skill) {
        if (skill == null) return false;
        String wanted = skill.trim().toUpperCase();
        for (String s : skills) {
            if (s.equals(wanted)) return true;
        }
        return false;
    }

    /** Free + healthy + enough battery. */
    public boolean isOperational() {
        return healthStatus && availability && batteryLevel >= 10;
    }

    // ---------- Battery ----------
    /** Adds charge (capped at 100). Returns false if amount is invalid or battery already full. */
    public boolean chargeBattery(int amount) {
        if (amount <= 0 || batteryLevel == MAX_BATTERY) return false;
        batteryLevel = Math.min(MAX_BATTERY, batteryLevel + amount);
        return true;
    }

    /** All-or-nothing: if there is not enough battery, nothing is deducted. */
    public boolean consumeBattery(int amount) {
        if (amount <= 0 || amount > batteryLevel) return false;
        batteryLevel -= amount;
        return true;
    }

    /** For the simulation's time step: drains but never goes below 0. */
    public void drainBattery(int amount) {
        if (amount <= 0) return;
        batteryLevel = Math.max(0, batteryLevel - amount);
    }

    public boolean changeAvailability() {
        if (!availability && !healthStatus) {
            return availability;
        }
        availability = !availability;
        return availability;
    }

    public boolean changeHealthStatus() {
        healthStatus = !healthStatus;
        if (!healthStatus) {
            availability = false;
        }
        return healthStatus;
    }

    public boolean setCurrentLocation(Facility location) {
        this.currentLocation = location;
        return true;
    }

    /** Fixes a damaged robot. Returns false if it was not damaged. Resource cost is handled by the repair mission. */
    public boolean repair() {
        if (healthStatus) return false;
        healthStatus = true;
        return true;
    }

    public boolean canPerform(Mission mission) {
        if (mission == null) return false;
        return isOperational() && hasSkill(mission.getRequiredCapability());
    }

    public boolean canResolve(Emergency emergency) {
        if (emergency == null) return false;
        return isOperational() && hasSkill(emergency.getRequiredCapability());
    }

    /** Shared helper used by the subclasses' operate...() methods. */
    protected boolean performAction(int batteryCost, String message) {
        if (!isOperational()) return false;
        if (!consumeBattery(batteryCost)) return false;
        System.out.println(name + ": " + message);
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + name + ", battery=" + batteryLevel
                + ", healthy=" + healthStatus + ", available=" + availability + "]";
    }
}