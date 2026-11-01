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

    private static final String[] names = {"john" , "harvey" , "thanos" , "peter" , "strange"};

    public ColonyManager() {
        people = new HumanPersonnel[20];
        facilities = new Facility[10];
        facilities[0] = new
        people[0] = new HumanPersonnel("peter" , 1 , {1 , 2} , )
    }
}


