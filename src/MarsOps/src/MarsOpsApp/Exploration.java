package MarsOpsApp;

import java.util.logging.Handler;

public class Exploration extends Mission {
    private  HumanPersonal personal;
    private  RoboticUnit robot;
    private  Equipments explorationEquipment;
    private  int [] SpecificRegion= new int [2];

    private static final Resources reqResource= new Resources (0,0,0,2,4,0,0);

    public Exploration(facilites facility, String name, HumanPersonal personal, int[] specificRegion,
                       Resources resource, Equipments explorationEquipment, RoboticUnit robot) {
        super(facility, name);
        this.personal = personal;
        SpecificRegion = specificRegion;
        this.resource = resource;
        this.explorationEquipment = explorationEquipment;
        this.robot = robot;
    }

    public int[] getSpecificRegion() {
        return SpecificRegion;
    }

    public Resources getResource() {
        return resource;
    }

    public Equipments getExplorationEquipment() {
        return explorationEquipment;
    }

    public RoboticUnit getRobot() {
        return robot;
    }

    public HumanPersonal getPersonal() {
        return personal;
    }
    public Object [] requirments(){
        Object req[] = new Object[12];
        req[0] = "scientist"; // human
        req[1] = "scientific skill"; // skill
        req[2] = "exploration rovor"; //robotqt

        req[4] = "exploration equipment"; //exploration equipment
        req[5] = null; //scientific equipment
        req[6]=true; // has the enough resources in the facility
        return req;
    }
    private  boolean assignPersonal(HumanPersonal personal){
        this.personal=personal;
        return true;
    }
    private  boolean assignRobot(RoboticUnit robot){
        this.robot=robot;
        return true;
    }
    private  boolean assignEquipment(Equipments equipment){
        this.explorationEquipment=equipment;
        return true;
    }
    public boolean missionFaild(int x){
        if(x==1)
            System.out.println("the scientist with the exploaration skiils  is unfound");
        else if (x==2)
            System.out.println("the robot is not found");
        else if (x==3)
            System.out.println("the fuel isn't enough ");
        else if (x==4)
            System.out.println("the battery power isn't  enough");
        else if ()
    }
    @Override
    public boolean startMission(Object[] found) {
        assignPersonal((HumanPersonal) found[0]);
        assignRobot((RoboticUnit) found[1]);
        assignEquipment((Equipemts)found[2]);
        changeStatus(MissionStatus.IN_PROGRESS);
        return false;
    }
}
