package Agents;

import FacilitiesAndResources.Facility;

public class Drone extends RoboticUnit {

    private static final int COST = 5;
 
    public Drone(String name, int batteryLevel, Facility location) {
        super(name, batteryLevel, location, new String[] {"AERIAL_RECON"});
    }
 
    public boolean operateDrone() {
        return performAction(COST, "scouting from the air.");
    }
}
