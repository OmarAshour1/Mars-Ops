package Missions;

import Agents.HumanPersonnel;
import FacilitiesAndResources.Facilities;
import FacilitiesAndResources.Resources;

public class Research extends Mission{
    private HumanPersonnel personnel;
    private  static final Resources reqResoruces=new Resources(0,3,2,2,4,0,4);;
    private Equipments researchEuipment;

    public Research(Facilities facility, String name, HumanPersonnel personnel, Equipments equipment) {
        super(facility, name);
        this.personnel = personnel;
        this.equipment = equipment;
    }
    public Object[] requirments() {
        Object req[] = new Object[7];
        req[0] = "scientist"; // human
        req[1] = "scientific skill"; // skill
        req[2] = null; //robotqt
        req[3] = "laboratory" // Facility
        req[4] = reqResoruces; // Requirment Resources
        req[5] = false; //exploration equipment
        req[6] = true; //scientific equipment
        return req;
    }
    private boolean assignHuman(HumanPersonnel personnel) {
        this.personnel = personnel;
        return true;
    }
    private boolean assignEquipment(Equipments equipment) {
        this.researchEuipment = equipment;
        return true;
    }
    @Override
    public boolean startMission(Object[] found) {
        // 0 --> person  1--> robot 2--> resources  3--> equipment
        if (found[0] == null) {
            missionFaild(0);
            return false;
        }
        if (found[3] == null) {
            missionFaild(6);
            return false;
        }
        if ((boolean) found[2] == false) {
            missionFaild(4);
            return false;
        }
        if ((Boolean) found[2] == true) {
            assignHuman((HumanPersonnel) found[0]);
            assignEquipment((Equipments) found[3]);
            //Resources r = facilityConsumeRes(found[3]);
            //colonyConsumeRes(r);  if the facility has enough , found[3] will be zeros
            System.out.println("Research is being performed...");
            this.changeStatus(MissionStatus.IN_PROGRESS);
            return true;
        }
        return true;
    }
}
