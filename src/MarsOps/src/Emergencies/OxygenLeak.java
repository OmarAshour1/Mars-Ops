package Emergencies;

import FacilitiesAndResources.Facility;

public class OxygenLeak extends Emergency {
    private static final double amountLeak = -20;
    private static final Resources resource = new Resources(0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 4.0);

    public OxygenLeak(String name, int[] location, Facility place) {
        //if place is not habbitat throw an error
        super(name, location, place);
        Boolean ret = place.changeResources("oxygen" ,amountLeak);
        if(ret) {
            if (place.getAvailability())
                place.changeAvailability();
            if (place.getOperational())
                place.changeOperationalStatus();
        }
        // else throw an error
    }
    public OxygenLeak(String name, Facility place) {
        //if place is not habbitat throw an error
        super(name, place.getLocation(), place);
        Boolean ret = place.changeResources("oxygen" ,amountLeak);
        if(ret) {
            if (place.getAvailability())
                place.changeAvailability();
            if (place.getOperational())
                place.changeOperationalStatus();

        }
        // else throw an error
    }

    public double getAmountLeak() {
        return -1 * amountLeak;
    }
    
    @Override
    public Object[] requirments(){
        Object req[] = new Object[12];
        req[0] = "engineer"; // human
        req[1] = "leakage"; // skill
        req[2] = "repairunit"; //robot
        req[3] = null; //Facility
        req[4] = resource; //resources
        req[5] = null; //exploration equipment
        req[6] = null;//scientific equipment
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
            assignHuman(found[0]);
            assignRobot(found[1]);
            //Resources r = facilityConsumeRes(found[3]);
            //colonyConsumeRes(r);  if the facility has enough , found[3] will be zeros
            System.out.println("emergency is being resolved...");
            setEmergencyStatus(EmergencyStatus.Resolving);
            return true;
        }

        return false;
    }
    
    
    
    
}
