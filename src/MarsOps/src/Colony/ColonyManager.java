package Colony;
import Agents.*;
import Emergencies.*;
import FacilitiesAndResources.*;
import Missions.*;
public class ColonyManager {

    private Facility[] facilities;
    private HumanPersonnel[] people;
    private RoboticUnit[] robots;
    private Resources colonyWidRes;
    private Resources usableResources;
    private Mission currentMission;
    private Emergency[] emergencies;
    private int time;
    public ColonyManager() {
        people=new HumanPersonnel[8];
        robots=new RoboticUnit[6];
        facilities= new Facility[10];
        facilities[5]= new Hab
        robots[0]=new ExplorationRover("zeko",100)

    }
}
