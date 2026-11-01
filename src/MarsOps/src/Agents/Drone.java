package Agents;

import FacilitiesAndResources.Facility;

public class Drone extends RoboticUnit {

    private static final int OPERATE_COST = 8;

    public Drone(int batteryLevel, Facility location) {
        super(batteryLevel, location, new String[] {"AERIAL_RECON"});
    }

    public boolean operateDrone() {
        return performAction(OPERATE_COST, "scouting from the air.");
    }
}
