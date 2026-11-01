package Missions;
import Agents.HumanPersonnel;
import FacilitiesAndResources.Facilities;
import FacilitiesAndResources.Resources;

import java.awt.*;

public class Rescue extends Mission{
    private HumanPersonnel personnel;
    private  RoboticUnit robot;
    private boolean commCap;
    private int [] location= new int[2];
    private static Resources reqResources=new Resources(0,0,0,2,4,0,4);
    public Rescue(Facilities facility, String name, HumanPersonnel personnel,
                  int[] location, boolean commCap, RoboticUnit robot) {
        super(facility, name);
        this.personnel = personnel;
        this.location = location;
        this.commCap = commCap;
        this.robot = robot;
    }
    public Object[] requirments() {
        Object req[] = new Object[7];
        req[0] = null; // human if null meaning any type of the personnel
        req[1] = "rescue skill"; // skill
        req[2] = "dron"; //robotqt
        req[3] = "laboratory" // Facility
        req[4] =reqResources ; // Requirment Resources
        req[5] = false; //exploration equipment
        req[6] = false; //scientific equipment
        return req;
    }

    public RoboticUnit getRobot() {
        return robot;
    }

    public HumanPersonnel getPersonnel() {
        return personnel;
    }

    public boolean isCommCap() {
        return commCap;
    }

    public int[] getLocation() {
        return location;
    }

    public static Resources getReqResources() {
        return reqResources;
    }

    public boolean startMission(Object[] found) {
        // 0 --> person  1--> robot 2--> resources  3--> equipment
        if (found[0] == null) {
            missionFaild(-1);
            return false;
        }
        if (found[1]==null){
            missionFaild(-2);
            return false;
        }
        if ((boolean) found[2] == false) {
            missionFaild(4);
            return false;
        }
        if ((boolean) found[2] == true) {
            assignHuman((HumanPersonnel) found[0]);
            assignRobot((RoboticUnit)found[1]);
            //Resources r = facilityConsumeRes(found[3]);
            //colonyConsumeRes(r);  if the facility has enough , found[3] will be zeros
            System.out.println("Research is being performed...");
            this.changeStatus(MissionStatus.IN_PROGRESS);
            return true;
        }
        return true;
    }
}
