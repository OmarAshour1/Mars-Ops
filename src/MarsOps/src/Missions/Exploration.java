package Missions;
import Agents.*;
import FacilitiesAndResources.*;

public class Exploration extends Mission {
    private HumanPersonnel personal;
    private RoboticUnit robot;
    private Equipment explorationEquipment;
    private int[] SpecificRegion = new int[2];
    private static final Resources reqResource = new Resources(0, 0, 0, 2, 4, 0, 0);

    public Exploration( int[] specificRegion) {
        super(null, "exploration");
        this.personal = null;
        SpecificRegion = specificRegion;
        this.explorationEquipment = null;
        this.robot = null;
    }

    public int[] getSpecificRegion() {
        return SpecificRegion;
    }

    public Resources getResource() {
        return reqResource;
    }

    public Equipment getExplorationEquipment() {
        return explorationEquipment;
    }

    public RoboticUnit getRobot() {
        return robot;
    }

    public HumanPersonnel getPersonal() {
        return personal;
    }

    public Object[] requirments() {
        Object [] req = new Object[7];
        req[0] = "scientist"; // human
        req[1] = "exploration skill"; // skill
        req[2] = "exploration rovor"; //robotqt
        req[3] = reqResource; // Requirment Resources
        req[4] = true; //exploration equipment
        req[5] = false; //scientific equipment
        return req;
    }

    private boolean assignHuman(HumanPersonnel personal) {
        this.personal = personal;
        return true;
    }

    private boolean assignRobot(RoboticUnit robot) {
        this.robot = robot;
        return true;
    }

    private boolean assignEquipment(Equipment equipment) {
        this.explorationEquipment = equipment;
        return true;
    }

    @Override
    public boolean startMission(Object[] found) {
        // 0 --> person  1--> robot 2--> resources  3--> equipment
        if (found[0] == null) {
            missionFaild(0);
            return false;
        }
        if (found[1] == null) {
            missionFaild(2);
            return false;
        }
        if (found[3] == null) {
            missionFaild(5);
            return false;
        }
        if ((boolean) found[2] == false) {
            missionFaild(4);
            return false;
        }
        if ((Boolean) found[2] == true) {
            assignHuman((HumanPersonnel) found[0]);
            assignRobot((RoboticUnit) found[1]);
            assignEquipment((Equipment) found[3]);
            //Resources r = facilityConsumeRes(found[3]);
            //colonyConsumeRes(r);  if the facility has enough , found[3] will be zeros
            System.out.println("Exploration is being performed...");
            this.changeStatus(MissionStatus.IN_PROGRESS);
            return true;
        }
        return true;
    }

    public Boolean endMission(int x){
        this.personnel.increaseFatigueLevel(1);
        this.personnel.changeAvailability();
        this.robot.changeAvailability();
        this.robot.consumeBattery(5);
        changeStatus(MissionStatus.COMPLETED);
        System.out.println("Mission Completed");
        return true;
    }
}
