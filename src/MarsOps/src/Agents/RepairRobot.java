package Agents;

import FacilitiesAndResources.Facility;

public class RepairRobot extends RoboticUnit {

    private static final int OPERATE_COST = 5;

    public RepairRobot(int batteryLevel, Facility location) {
        super(batteryLevel, location, new String[] {"REPAIR"});
    }

    public boolean operateRepair() {
        return performAction(OPERATE_COST, "repairing equipment.");
    }

}
