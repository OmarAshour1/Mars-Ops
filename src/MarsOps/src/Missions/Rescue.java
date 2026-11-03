package Missions;
import Agents.HumanPersonnel;
import FacilitiesAndResources.Equipment;
import FacilitiesAndResources.Facility;
import FacilitiesAndResources.Resources;
import Agents.RoboticUnit;
import java.awt.*;

public class Rescue extends Mission{
    private  RoboticUnit robot;
    private boolean commCap;
    private int [] location= new int[2];
    private Object target;
    private int x;
    private static Resources reqResources=new Resources(0,0,0,2,4,0,4);
    public Rescue(int[] location, boolean commCap,Object target) {
        super(null, "rescue");
        this.personnel = null;
        this.location = location;
        this.commCap = commCap;
        if(target instanceof HumanPersonnel)
            this.x=0;
        else
            this.x=1;
        this.target=target;
        this.robot = null;
    }
    public Object[] requirments() {
        Object req[] = new Object[7];
        req[0] = null; // human if null meaning any type of the personnel
        req[1] = "rescue skill"; // skill
        req[2] = "dron"; //robotqt
        req[3] = "laboratory"; // Facility
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

    protected Boolean assignHuman(HumanPersonnel person){
        if(!person.getAvailability())
            return false;
        personnel = person;
        personnel.changeAvailability();
        personnel.increaseFatigueLevel(5);
        return true;
    }

    protected Boolean assignRobot(RoboticUnit robot){
        if(!robot.getAvailability())
            return false;
        this.robot = robot;
        this.robot.changeAvailability();
        this.robot.consumeBattery(10);
        return true;
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
            System.out.println("Rescue is being performed...");
            this.changeStatus(MissionStatus.IN_PROGRESS);
            return true;
        }

        return true;
    }
    public Boolean endMission(){
        this.personnel.increaseFatigueLevel(1);
        this.personnel.changeAvailability();
        this.robot.changeAvailability();
        this.robot.consumeBattery(5);
        changeStatus(MissionStatus.COMPLETED);
        if (this.x==0){
            HumanPersonnel p= (HumanPersonnel) this.target;
            p.decreaseFatigueLevel(5);
            p.changeAvailability();
            p.changeHealthStatus(true);
        }
        if(this.x==1){
            RoboticUnit p=(RoboticUnit) this.target;
            p.changeAvailability();
            p.changeHealthStatus();
            p.chargeBattery(100);
        }
        System.out.println("Mission Completed");
        return true;
    }
}
