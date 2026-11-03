package Missions;
import Agents.HumanPersonnel;
import Agents.RoboticUnit;
import FacilitiesAndResources.*;

public class Repair extends Mission{
    private RoboticUnit robot;
    private  static final Resources reqResoruces=new Resources(0,0,0,0,0,0,4); ;
    private  Equipment equipment;
    private int x;

    public Repair(Facility facility) {
        super(facility, "repair");
        this.personnel = null;
        this.equipment = null;
        this.robot = null;
        this.x=1;
    }

    public Repair(Equipment equipment) {
        super(null, "repair");
        this.personnel = null;
        this.equipment = equipment;
        this.robot = null;
        this.x=0;
    }

    public HumanPersonnel getPersonnel() {
        return personnel;
    }

    public RoboticUnit getRobot() {
        return robot;
    }
    public Object[] requirments() {
        Object req[] = new Object[7];
        req[0] = "engineer"; // human
        req[1] = "repairing skill"; // skill
        req[2] = "repairrobot"; //robotqt
        req[3] =null;
        req[4] = reqResoruces; // Requirment Resources
        req[5] = false; //exploration equipment
        req[6] = false; //scientific equipment
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
        // 0--> Person  1--> Robot   2-->resources  3-->equipment 4-->facility
        if (found[0]==null){
            missionFaild(1);
            return false;
        }
        else if (found[1]==null){
            missionFaild(3);
            return false;
        }
        else if ((boolean) found[2] == false){
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

    public Boolean endMission(){
        this.personnel.increaseFatigueLevel(1);
        this.personnel.changeAvailability();
        this.robot.changeAvailability();
        this.robot.consumeBattery(5);
        changeStatus(MissionStatus.COMPLETED);
        if (this.x==0){
            this.equipment.changeAvailability() ;
        }
        if(this.x==1){
           this.facility.changeAvailability();
           this.facility.changeOperationalStatus();
        }
        System.out.println("Mission Completed");
        return true;
    }

}
