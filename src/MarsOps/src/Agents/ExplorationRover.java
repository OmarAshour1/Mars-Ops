package Agents;

import FacilitiesAndResources.Facility;

public class ExplorationRover extends RoboticUnit {

    private static final int OPERATE_COST = 10;

    public ExplorationRover(String name,int batteryLevel, Facility location,int []place) {
        super(name ,batteryLevel, location, new String[] {"EXPLORATION", "RESCUE"},place);
    }

    public boolean operateRover() {
        return performAction(OPERATE_COST, "driving across the Martian surface.");
    }

}