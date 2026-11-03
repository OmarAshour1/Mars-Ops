package Emergencies;

import Agents.ExplorationRover;
import Agents.HumanPersonnel;
import Agents.RoboticUnit;
import FacilitiesAndResources.Facility;
import FacilitiesAndResources.Resources;

public class RoverBreakdown extends Emergency{
    private static final Resources resource = new Resources(0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 2.0);
    private ExplorationRover rover ;

    public RoverBreakdown(String name, int[] location , ExplorationRover rover) {
        super(name, location, null);
        if(!rover.getAvailability() && rover.getHealthStatus()){
            this.rover = rover;
            this.rover.changeHealthStatus();
        }

    }

    @Override
    public Object[] requirments(){
        Object req[] = new Object[12];
        req[0] = "engineer"; // human
        req[1] = "repairing skill"; // skill
        req[2] = "repairrobot"; //robot
        req[3] = null; //facility
        req[4] = resource; //resources
        req[5] = null; //exploration equipment
        req[6] = null; //scientific equipment
        return req;
    }

    private void unableToResolve(int x){
        if(x == 0){
            System.out.println("We can't find any available or even capable Person.....");
        }
        else if(x == 1){
            System.out.println("We can't find any available or even capable Robot.....");
        }
        else if(x == 2){
            System.out.println("No enough spare parts.....");

        }
    }

    @Override
    public Boolean resolved() {
        System.out.println("emergency is resolved...");
        setEmergencyStatus(EmergencyStatus.Resolved);
        this.rover.changeHealthStatus();
        this.rover.changeAvailability();

        super.getPersonnel().changeAvailability();
        super.getRobot().changeAvailability();

        return true;
    }

    @Override
    public Boolean resolve(Object found[]) {
        if(found[0] == null){
            unableToResolve(0);
            return false;
        }
        if(found[1] == null){
            unableToResolve(1);
            return false;
        }
        if((Boolean)found[2] == false){
            unableToResolve(2);
            return false;
        }
        if((Boolean)found[2] == true){
            assignHuman((HumanPersonnel) found[0]);
            assignRobot((RoboticUnit)found[1]);
            System.out.println("emergency is being resolved...");
            setEmergencyStatus(EmergencyStatus.Resolving);
            return true;
        }

        return false;
    }
}
