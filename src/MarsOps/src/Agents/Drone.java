package Agents;

import FacilitiesAndResources.Facility;

public class Drone extends RoboticUnit {

    private static final int COST = 5;

    public Drone(String name, int batteryLevel, Facility location,int []place) {
        super(name, batteryLevel, location, new String[] {"AERIAL_RECON"},place);
    }

    public boolean operateDrone() {
        return performAction(COST, "scouting from the air.");
    }

}