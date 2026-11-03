package Emergencies;

import Agents.HumanPersonnel;
import Agents.RoboticUnit;
import FacilitiesAndResources.Equipment;
import FacilitiesAndResources.Facility;
import FacilitiesAndResources.Resources;

public class Fire extends Emergency{
    private static final Resources resource = new Resources(0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 10.0 , 0.0);

    public Fire(String name, int [] location ,Facility place) {
        //if place is not Lab throw an error
        // increase oxygen consumbion
        super(name, location , place);
        Equipment eqs[] = place.getEquipments();
        for(Equipment u : eqs){
            if(u.getStatus()){
                u.changeStatus();
                u.makeUnavailable();
            }
        }
        if (place.getAvailability())
            place.changeAvailability();
        if (place.getOperationalStatus())
            place.changeOperationalStatus();

        // else throw an error
    }

    public Fire(String name,Facility place) {
        super(name, place.getLocation() , place);
        Equipment[] eqs = place.getEquipments();
        for(Equipment u : eqs){
            if(u.getStatus()){
                u.changeStatus();
                u.makeUnavailable();
            }
        }

        if (place.getAvailability())
            place.changeAvailability();

        if (place.getOperationalStatus())
            place.changeOperationalStatus();

        // else throw an error
    }

    @Override
    public Object[] requirments(){
        Object req[] = new Object[12];
        req[0] = "MedicalOfficer"; // human
        req[1] = "fireFighting"; // skill
        req[2] = "repairunit"; //robot
        req[3] = null; //facility
        req[4] = resource; //resources
        req[5] = null; //exploration equipment
        req[6] = null; //scientific equipment
        return req;
    }

    @Override
    public Boolean resolved() {
        System.out.println("emergency is resolved...");
        setEmergencyStatus(EmergencyStatus.Resolved);
        super.getPlace().changeAvailability();
        super.getPlace().changeOperationalStatus();
        for(Equipment u : getPlace().getEquipments()){
            if(!u.getStatus()){
                u.changeStatus();
                u.changeAvailability();
            }
        }

        super.getPersonnel().changeAvailability();
        super.getRobot().changeAvailability();

        return true;
    }

    private void unableToResolve(int x){
        if(x == 0){
            System.out.println("We can't find any available or even capable Person.....");
        }
        else if(x == 1){
            System.out.println("We can't find any available or even capable Robot.....");
        }
        else if(x == 2){
            System.out.println("No enough Medical supplies.....");

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
            assignHuman((HumanPersonnel) found[0]);
            assignRobot((RoboticUnit) found[1]);
            System.out.println("emergency is being resolved...");
            setEmergencyStatus(EmergencyStatus.Resolving);
            return true;
        }

        return false;
    }
}
