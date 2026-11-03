package Emergencies;

import Agents.HumanPersonnel;
import Agents.RoboticUnit;
import FacilitiesAndResources.Facility;
import FacilitiesAndResources.Resources;

public class OxygenLeak extends Emergency {
    private static final Resources amountLeak = new Resources(20.0 , 0.0 , 0.0 , 0.0 ,0.0 ,0.0 , 0.0 );
    private static final Resources resource = new Resources(0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 4.0);

    public OxygenLeak(String name, int[] location, Facility place) {
        //if place is not habitat throw an error
        super(name, location, place);
        Resources ret = place.facilityConsumeResource(amountLeak);

        if (place.getAvailability())
                place.changeAvailability();
        if (place.getOperationalStatus())
                place.changeOperationalStatus();

    }

    public OxygenLeak(String name, Facility place) {
        //if place is not habitat throw an error
        super(name, place.getLocation(), place);
        Resources ret = place.facilityConsumeResource(amountLeak);

        if (place.getAvailability())
            place.changeAvailability();
        if (place.getOperationalStatus())
            place.changeOperationalStatus();
    }

    public double getAmountLeak() {
        return 20;
    }
    
    @Override
    public Object[] requirments(){
        //resource =getPlace().getResources().sub(resource);
        Object req[] = new Object[12];
        req[0] = "engineer"; // human
        req[1] = "leakage"; // skill
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
        super.getPlace().changeAvailability();
        super.getPlace().changeOperationalStatus();

        super.getPersonnel().changeAvailability();
        super.getRobot().changeAvailability();

        return true;
    }
    
    @Override
    public Boolean resolve(Object[] found) {
        if(found[0] == null){
            unableToResolve(0);
            Resources ret = super.getPlace().facilityConsumeResource(amountLeak);
            return false;
        }
        if(found[1] == null){
            unableToResolve(1);
            Resources ret = super.getPlace().facilityConsumeResource(amountLeak);
            return false;
        }
        if((Boolean)found[2] == false){
            unableToResolve(2);
            Resources ret = super.getPlace().facilityConsumeResource(amountLeak);
            return false;
        }
        if((Boolean)found[2] == true){
            assignHuman((HumanPersonnel) found[0]);
            assignRobot((RoboticUnit)found[1]);
            System.out.println("emergency is being resolved...");
            setEmergencyStatus(EmergencyStatus.Resolving);
            return true;
        }
        Resources ret = super.getPlace().facilityConsumeResource(amountLeak);
        return false;
    }
    
    
    
    
}
