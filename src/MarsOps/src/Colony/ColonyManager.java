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
    private Environment env;
    public ColonyManager() {
        people = new HumanPersonnel[8];
        facilities = new Facility[10];
        robots=new RoboticUnit[6];
        facilities[0] = new CommunicationStations("CommunicationStation1" , new int[]{1, 1});
        facilities[1] = new CommunicationStations("CommunicationStation2" , new int[]{2, 2});
        facilities[2] = new PowerStations("PowerStation1" , new int[]{3, 3});
        facilities[3] = new PowerStations("PowerStation1" , new int[]{4, 4});
        facilities[4] = new Laboratories("Laboratory" , new int[]{2, 3});
        facilities[5]=new Habitat("Habitat1", new int[]{5,5});
        facilities[6]=new Habitat("Habitat2", new int[]{5,6});
        facilities[7]=new Habitat("Habitat3", new int[]{5,7});
        facilities[8]=new StorageFacilities("StorageFacility1", new int[]{5,8});
        facilities[9]=new StorageFacilities("StorageFacility2", new int[]{5,9});
        robots[0]=new ExplorationRover("zeko",100,null,new int[]{10,10});
        robots[1]=new ExplorationRover("beko",100,null,new int[]{10,20});
        robots[2]=new Drone("reko",100,facilities[0],facilities[0].getLocation());
        robots[3]=new Drone("heko",100,facilities[1],facilities[1].getLocation());
        robots[4]=new RepairRobot("meko",100,facilities[8],facilities[8].getLocation());
        robots[5]=new RepairRobot("keko",100,facilities[5],facilities[5].getLocation());
        people[0] = new HumanPersonnel("Tony stark",1,new int[]{1, 1},facilities[0],new String[] {"exploration skill" , "fireFighting"},"human");
        people[1] = new HumanPersonnel("Thanos" , 2 , new int[]{2, 2} , facilities[1] , new String[] {"rescue skill" , "fireFighting"},"human");
        people[2] = new Engineer("Peter Parker" , 3 , new int[]{3 ,3} , facilities[2] , new String[] {"repairing skill" , "fireFighting" , "rescue skill" , "exploration skill"});
        people[3] = new Engineer("Steve Rogers" , 4 , new int[]{4 ,4} , facilities[3] , new String[] {"repairing skill" , "fireFighting" , "rescue skill" , "exploration skill"});
        people[4] = new Scientist("Natasha" , 5 , new int[]{2 ,3} , facilities[4] , new String[] {"exploration skill" , "fireFighting" , "rescue skill" , "scientific skill"});
        people[5] = new Scientist("Bruce Banner" , 6 , new int[]{5,5} , facilities[5] , new String[] {"exploration skill" , "fireFighting" , "rescue skill" , "scientific skill"});
        people[6] = new MedicalOfficer("Thor" , 7 , new int[]{5,7} , facilities[6] , new String[] {"firstaid" , "fireFighting" , "rescue skill" , "scientific skill"});
        people[7] = new MedicalOfficer("mahmoud" , 8, new int[]{5,6} , facilities[7] , new String[] {"firstaid" , "fireFighting" , "rescue skill" , "scientific skill"});
        colonyWidRes=new Resources(0,0,0,0,0,0,0);
        usableResources=new Resources(0,0,0,0,0,0,0);
        for (Facility facility:facilities){
            colonyWidRes.add(facility.getResources());
            usableResources.add(facility.getResources());
        }
        currentMission=null;
        emergencies=new Emergency[5];
        time=0;
        env=new Environment();
    }

    public Facility[] getFacilities() {
        return facilities;
    }

    public HumanPersonnel[] getPeople() {
        return people;
    }

    public RoboticUnit[] getRobots() {
        return robots;
    }

    public Resources getColonyWidRes() {
        return colonyWidRes;
    }

    public Resources getUsableResources() {
        return usableResources;
    }

    public Mission getCurrentMission() {
        return currentMission;
    }

    public Emergency[] getEmergencies() {
        return emergencies;
    }

    public int getTime() {
        return time;
    }

    public boolean colonyReport(){
        System.out.println(this.getColonyWidRes());
        System.out.println(this.getCurrentMission());
        System.out.println(this.getEmergencies());
        System.out.println(this.getFacilities());
        System.out.println(this.getPeople());
        System.out.println(this.getRobots());
        System.out.println(this.getTime());
        System.out.println(this.getUsableResources());
        return true;
    }
    public boolean changeEnvironment(String s){
        if(s==null)
            return false;
        else if (s.toLowerCase().equals("dust"))
            env.changeToDust();
        else if (s.toLowerCase().equals("normal"))
            env.changeToNormalCondition();
        else
            env.changeToCommDisruption();
        return true;
    }
    public boolean applyForAMission(String s){
        Object [] found =new Object[4];
        if (this.currentMission!=null){
            System.out.println("Invaild there is a Mission in progress");
            return false;
        }
        if(s.equals("research")){
            this.currentMission=new Research();
            Object[] req=this.currentMission.requirments();
            // search for the scientists
            for (HumanPersonnel personnel:this.people){
                if (personnel.canPerform(this.currentMission)){
                    found[0]=personnel;
                    break;
                }
            }
        }

    }
}


