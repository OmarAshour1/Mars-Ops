package Agents;

import FacilitiesAndResources.Facility;

public class RepairRobot extends RoboticUnit {
 
    private static final int COST = 5;
 
    public RepairRobot(String name, int batteryLevel, Facility location) {
        super(name, batteryLevel, location, new String[] {"REPAIR"});
    }
 
    public boolean operateRepair() {
        return performAction(COST, "repairing equipment.");
    }
}
