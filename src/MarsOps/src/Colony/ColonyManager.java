package Colony;


import Agents.*;
import Emergencies.*;
import FacilitiesAndResources.*;
import Missions.*;

import java.util.Scanner;

import java.util.Objects;

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
    private Equipment[] equipments;
    private int cnt = 0;
    private int emergcnt;
    private int consumption;

    public ColonyManager() {
        people = new HumanPersonnel[8];
        facilities = new Facility[10];
        robots = new RoboticUnit[6];
        equipments = new Equipment[4];

        facilities[0] = new PowerStations("PowerStation1", new int[]{3, 3});
        facilities[1] = new PowerStations("PowerStation1", new int[]{4, 4});
        facilities[2] = new StorageFacilities("StorageFacility1", new int[]{5, 8});
        facilities[3] = new StorageFacilities("StorageFacility2", new int[]{5, 9});
        facilities[4] = new Laboratories("Laboratory", new int[]{2, 3});
        facilities[5] = new Habitat("Habitat1", new int[]{5, 5});
        facilities[6] = new Habitat("Habitat2", new int[]{5, 6});
        facilities[7] = new Habitat("Habitat3", new int[]{5, 7});
        facilities[8] = new CommunicationStations("CommunicationStation1", new int[]{1, 1});
        facilities[9] = new CommunicationStations("CommunicationStation2", new int[]{2, 2});

        robots[0] = new ExplorationRover("zeko", 100, null, new int[]{10, 10});
        robots[1] = new ExplorationRover("beko", 100, null, new int[]{10, 20});
        robots[2] = new Drone("reko", 100, facilities[0], facilities[0].getLocation());
        robots[3] = new Drone("heko", 100, facilities[1], facilities[1].getLocation());
        robots[4] = new RepairRobot("meko", 100, facilities[8], facilities[8].getLocation());
        robots[5] = new RepairRobot("keko", 100, facilities[5], facilities[5].getLocation());

        people[0] = new HumanPersonnel("Tony stark", 1, new int[]{1, 1}, facilities[0], new String[]{"exploration skill", "fireFighting"}, "human");
        people[1] = new HumanPersonnel("Thanos", 2, new int[]{2, 2}, facilities[1], new String[]{"rescue skill", "fireFighting"}, "human");
        people[2] = new Engineer("Peter Parker", 3, new int[]{3, 3}, facilities[2], new String[]{"repairing skill", "fireFighting", "rescue skill", "exploration skill"});
        people[3] = new Engineer("Steve Rogers", 4, new int[]{4, 4}, facilities[3], new String[]{"repairing skill", "fireFighting", "rescue skill", "exploration skill"});
        people[4] = new Scientist("Natasha", 5, new int[]{2, 3}, facilities[4], new String[]{"exploration skill", "fireFighting", "rescue skill", "scientific skill"});
        people[5] = new Scientist("Bruce Banner", 6, new int[]{5, 5}, facilities[5], new String[]{"exploration skill", "fireFighting", "rescue skill", "scientific skill"});
        people[6] = new MedicalOfficer("Thor", 7, new int[]{5, 7}, facilities[6], new String[]{"firstaid", "fireFighting", "rescue skill", "scientific skill"});
        people[7] = new MedicalOfficer("mahmoud", 8, new int[]{5, 6}, facilities[7], new String[]{"firstaid", "fireFighting", "rescue skill", "scientific skill"});

        equipments[0] = new ExplorationEquipment("expEquipment1");
        equipments[1] = new ExplorationEquipment("expEquipment2");
        equipments[2] = new ScientificEquipment("scientificEquipment1");
        equipments[3] = new ScientificEquipment("scientificEquipment2");

        colonyWidRes = new Resources(0, 0, 0, 0, 0, 0, 0);
        usableResources = new Resources(0, 0, 0, 0, 0, 0, 0);
        for (Facility facility : facilities) {
            colonyWidRes.add(facility.getResources());
            usableResources.add(facility.getResources());
        }
        currentMission = null;
        emergencies = new Emergency[5];
        emergcnt = 0;
        time = 0;
        env = new Environment();
        consumption = 1;
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

    public boolean colonyReport() {
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

    public boolean changeEnvironment(String s) {
        if (s == null)
            return false;
        else if (s.toLowerCase().equals("dust"))
            env.changeToDust();
        else if (s.toLowerCase().equals("normal"))
            env.changeToNormalCondition();
        else
            env.changeToCommDisruption();
        return true;
    }

    public boolean applyForAMission(String s) {
        if (this.currentMission != null) {
            System.out.println("Invaild there is a Mission in progress");
            return false;
        }
        if (s.equals("research")) {
            this.currentMission = new Research();
        } else if (s.equals("rescue")) {
            people[1].changeHealthStatus(false);
            people[1].changeAvailability();
            this.currentMission = new Rescue(people[1].getLocation(), !this.env.isCommDisruption(), people[1]);
        } else if (s.equals("exploration")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter the location of the specific regin x,y : ");
            int[] location = new int[2];
            location[0] = scanner.nextInt();
            location[1] = scanner.nextInt();
            this.currentMission = new Exploration(location);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("choose you want to rapir personnel or facility ");
            int x = scanner.nextInt();
            if (x == 0) {
                facilities[3].changeOperationalStatus();
                facilities[3].changeAvailability();
                this.currentMission = new Repair(facilities[3]);
            } else if (x == 1) {
                equipments[0].changeAvailability();
                this.currentMission = new Repair(equipments[0]);
            }

        }


        Object [] found =new Object[5];
            Object[] req=this.currentMission.requirments();
            // search for the human
            for (HumanPersonnel personnel:this.people){
                if (personnel.canPerform(this.currentMission)){
                    found[0]=personnel;
                    break;
                }
            }
            // search for the robot
            for( RoboticUnit robot : this.robots){
                if(robot.canPerform(this.currentMission)){
                    found[1]=robot;
                    break;
                }
            }
            // check for the facility
            for(Facility facility: this.facilities){
                if(found[4]==null)
                    break;;
                if(facility instanceof Laboratories &&facility.getAvailability()&&facility.getOperationalStatus() ){
                    found[4]=facility;
                }
            }
            // search for equipment
            for(Equipment equ:equipments){
                if((boolean)req[4]==true){
                    if(equ instanceof ExplorationEquipment&&equ.getAvailability()&&equ.getStatus()) {
                        found[3] = equ;
                        break;
                    }
                }
                if((boolean)req[5]==true){
                    if(equ instanceof ScientificEquipment&&equ.getAvailability()&&equ.getStatus()) {
                        found[3] = equ;
                        break;
                    }
                }
            }
            // check Resources
            if(usableResources.comp((Resources) req[4])){
                found[2]=true;
            }
            else
                found[2]=false;
            boolean check =this.currentMission.startMission(found);
            if(check){
                System.out.println(" so the Mission can't be In Progress");
                this.currentMission=null;
                return false;
            }
            else{
                System.out.println("the Mission is In Progress ");
                return true;
            }
    }




    public void consumeFacilityResource(Resources r){
        Resources res = r;
        for(Facility f : facilities){
            res = f.facilityConsumeResource(res);
        }
        updateColonyResources();
    }

    public Boolean Emerge(String s) {
        if (emergcnt == 5) {
            System.out.println("Can't add another Emergencies....");
            return false;
        }
        if (s.equals("oxygenleak")) {
            Emergency e = new OxygenLeak("oxygenleak", facilities[5 + cnt].getLocation(), facilities[5 + cnt]);
            cnt++;
            cnt %= 3;
            emergencies[emergcnt++] = e;
        }
        else if (s.equals("fire")) {
            Emergency e = new Fire("fire", facilities[4].getLocation(), facilities[4]);
            emergencies[emergcnt++] = e;
            consumption = 2;
        }
        else if(s.equals("medicalemergency")){
            if(people[emergcnt].getHealthStatus()){
                people[emergcnt].changeHealthStatus(false);
            }
            Emergency e = new MedicalEmergency("medicalemergency", people[emergcnt].getPlace(), people[emergcnt]);
            emergencies[emergcnt++] = e;
        }
        else if(s.equals("roverbreak")){
            if(!robots[0].getAvailability() && robots[0].getHealthStatus()){
                Emergency e = new RoverBreakdown("roverbreakdown", robots[0].getLocation() , (ExplorationRover) robots[0]);
                emergencies[emergcnt++] = e;
            }
            else if (!robots[1].getAvailability() && robots[1].getHealthStatus()){
                Emergency e = new RoverBreakdown("roverbreakdown", robots[1].getLocation() , (ExplorationRover) robots[1]);
                emergencies[emergcnt++] = e;
            }
            else {
                System.out.println("No rover assigned to a mission to make an Emergency......");
                return false;
            }
        }
        Object[] found = new Object[5];
        for(HumanPersonnel h : people){
            if(h.canResolve(emergencies[emergcnt-1])) {
                found[0] = h;
                break;
            }
        }

        for(RoboticUnit r : robots){
            if(r.canResolve(emergencies[emergcnt-1])){
                found[1] = r;
                break;
            }
        }

        if(usableResources.comp((Resources)emergencies[emergcnt-1].requirments()[4])){
            found[2] = true;
        }
        else found[2] = false;

        Boolean ret = emergencies[emergcnt-1].resolve(found);
        if(!ret) return false;
        if(emergencies[emergcnt-1].getStatus()== Emergency.EmergencyStatus.Resolving){
            Resources r = emergencies[emergcnt-1].getPlace().facilityConsumeResource((Resources)emergencies[emergcnt-1].requirments()[4]);
            consumeFacilityResource(r);
        }
        return true;
    }



    public void updateColonyResources(){
        colonyWidRes = new Resources(0,0,0,0,0,0,0);
        usableResources = new Resources(0 , 0 , 0 , 0, 0, 0,0);
        for (Facility facility:facilities){
            colonyWidRes.add(facility.getResources());
            usableResources.add(facility.getUsableRes());
        }
    }

    public void advanceTime(int t){
        if(currentMission != null){
            if(currentMission.getStatus() == Mission.MissionStatus.IN_PROGRESS) {
                if (!currentMission.getPerson().getHealthStatus()) {
                    currentMission.stopMission();
                } else if (!currentMission.getRobot().isOperational()) {
                    currentMission.stopMission();
                } else if (!currentMission.getFacility().getOperationalStatus()) {
                    currentMission.stopMission();
                } else if (Objects.equals(currentMission.getName(), "rescue") && env.isCommDisruption()) {
                    currentMission.stopMission();
                } else if (Objects.equals(currentMission.getName(), "exploration") && env.isDust()) {
                    currentMission.stopMission();
                }
                if (currentMission.getStatus() == Mission.MissionStatus.IN_PROGRESS)
                    currentMission.increaseTimer(t);
                if (currentMission.getTimer() >= 3) {
                    currentMission.endMission();
                }
            }
        }

        for(Emergency emergency : emergencies){
            if(emergency != null){
                if(emergency.getStatus() == Emergency.EmergencyStatus.Resolving){
                    if(!emergency.getPersonnel().getHealthStatus()){
                        emergency.unresolve();
                    }
                    else if(!emergency.getRobot().isOperational()){
                        emergency.unresolve();
                    }
                    else if(!emergency.getPlace().getOperationalStatus()){
                        emergency.unresolve();
                    }
                    if(emergency.getStatus() == Emergency.EmergencyStatus.Resolving)
                        emergency.increaseTimer(t);
                    if(emergency.getTimer() >= 3){
                        if(emergency.getName().equals("fire"))
                            consumption = 1;
                        emergency.resolved();
                    }

                }
            }
        }

        for(Facility facility : facilities){
            Resources rec;
            if(facility.getName() == "laboratory")
                rec = new Resources(1*consumption , 1 , 1,1,1,1 ,1);
            else rec = new Resources(1 , 1, 1, 1, 1, 1, 1 );
            if(facility.getUsableRes().comp(rec)) {
                facility.facilityConsumeResource(rec);
            }
            else if(facility.getOperationalStatus()){
                facility.changeOperationalStatus();
                if(facility.getAvailability())
                    facility.changeAvailability();
            }
        }

        updateColonyResources();

        time++;
    }


}


