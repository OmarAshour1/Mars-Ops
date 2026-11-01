package Missions;
import Agents.HumanPersonnel;
import Agents.RoboticUnit;
import FacilitiesAndResources.*;

public class Repair extends Mission{
    private HumanPersonnel personnel;
    private RoboticUnit robot;
    private  static final Resources reqResoruces=new Resources(0,0,0,0,0,0,4); ;
    private  Equipment equipment;

    public Repair(Facility facility, String name, HumanPersonnel personnel,
                   Resources resource, RoboticUnit robot) {
        super(facility, name);
        this.personnel = personnel;
        this.equipment = null;
        this.robot = robot;
    }

    public Repair(Equipment equipment, String name, HumanPersonnel personnel,
                  Resources resource, RoboticUnit robot) {
        super(null, name);
        this.personnel = personnel;
        this.equipment = equipment;
        this.robot = robot;
    }

    public HumanPersonnel getPersonnel() {
        return personnel;
    }

    public RoboticUnit getRobot() {
        return robot;
    }
    public Object[] requirments() {
        Object req[] = new Object[7];
        req[0] = "enginner"; // human
        req[1] = "repairing skill"; // skill
        req[2] = "rapair robot"; //robotqt
        req[3] = reqResoruces; // Requirment Resources
        req[4] = false; //exploration equipment
        req[5] = false; //scientific equipment
        return req;
    }

    private boolean assignHuman(HumanPersonnel personnel) {
        this.personnel = personnel;
        return true;
    }

    private boolean assignRobot(RoboticUnit robot) {
        this.robot = robot;
        return true;
    }
    public boolean startMission(Object [] found ){
        // 0--> Person  1--> Robot  2-->resources  3-->equipment
        if (found[0]==null){
            missionFaild(1);
            return false;
        }
        else if (found[1]==null){
            missionFaild(3);
            return false;
        }
        else if ((boolean) found[2] == false ){
            missionFaild(4);
            return false;
        }
        else if ((boolean)found[2]==true){
            assignHuman((HumanPersonnel) found[0]);
            assignRobot((RoboticUnit) found[1]);
            //Resources r = facilityConsumeRes(reqResources);
            //colonyConsumeRes(r);  if the facility has enough , found[3] will be zeros
            System.out.println("Repair is being performed...");
            this.changeStatus(MissionStatus.IN_PROGRESS);
            return true;
        }
        return true;
    }

}
