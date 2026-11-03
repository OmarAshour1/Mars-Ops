package Missions;
import Agents.HumanPersonnel;
import Agents.RoboticUnit;
import FacilitiesAndResources.*;

public class Research extends Mission{
    private  static final Resources reqResoruces=new Resources(0,3,2,2,4,0,4);;
    private Equipment researchEuipment;

    public Research( ) {
        super(null, "research");
        this.personnel = null;
        this.researchEuipment = null ;
        this.robot=null;
    }
    public Object[] requirments() {
        Object req[] = new Object[7];
        req[0] = "scientist"; // human
        req[1] = "scientific skill"; // skill
        req[2] = "repairrobot"; //robotqt
        req[3] = "laboratory" ;// Facility
        req[4] = reqResoruces; // Requirment Resources
        req[5] = false; //exploration equipment
        req[6] = true; //scientific equipment
        return req;
    }
    private boolean assignHuman(HumanPersonnel personnel) {
        this.personnel = personnel;
        return true;
    }
    private boolean assignEquipment(Equipment equipment) {
        this.researchEuipment = equipment;
        return true;
    }

    private boolean assignRobot(RoboticUnit robot) {
        this.robot = robot;
        return true;
    }

    @Override
    public boolean startMission(Object[] found) {
        // 0 --> person  1--> robot 2--> resources  3--> equipment 4-->facility
        if (found[0] == null) {
            missionFaild(0);
            return false;
        }
        if (found[3] == null) {
            missionFaild(5);
            return false;
        }

        if(found[4]==null){
            missionFaild(7);
            return false;
        }
        if ((boolean) found[2] == false) {
            missionFaild(4);
            return false;
        }
        if ((Boolean) found[2] == true) {
            assignHuman((HumanPersonnel) found[0]);
            assignEquipment((Equipment) found[3]);
            assignRobot((RoboticUnit)found[1]);
            //Resources r = facilityConsumeRes(found[3]);
            //colonyConsumeRes(r);  if the facility has enough , found[3] will be zeros
            System.out.println("Research is being performed...");
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
        System.out.println("Mission Completed");
        return true;
    }
}
