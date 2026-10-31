package Emergencies;

import Agents.HumanPersonnel;
import FacilitiesAndResources.Facility;

public class MedicalEmergency extends Emergency{
    private HumanPersonnel patient;
    private static final Resources resource = new Resources(0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 5.0 , 0.0);

    public MedicalEmergency(String name, HumanPersonnel patient) {
        super(name, patient.getCurrentLocation() , patient.getPlace());
        this.patient = patient;
        if(this.patient.getAvailability())
            this.patient.changeAvailability();
        if(this.patient.getHealthStatus()) {
            this.patient.changeHealthStatus(false);
            this.patient.increaseFatigueLevel(100);
        }
    }

    public MedicalEmergency(String name, Facility place, HumanPersonnel patient) {
        super(name, place.getLocation() , place);
        this.patient = patient;
        if(this.patient.getAvailability())
            this.patient.changeAvailability();
        if(this.patient.getHealthStatus()) {
            this.patient.changeHealthStatus(false);
            this.patient.increaseFatigueLevel(100);
        }
    }

    @Override
    public Object[] requirments(){
        Object req[] = new Object[12];
        req[0] = "medicalofficer"; // human
        req[1] = "firstaid"; // skill
        req[2] = "rover"; //robot
        req[3] = resource; //resources
        req[4] = null; //exploration equipment
        req[5] = null; //scientific equipment
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
            System.out.println("No enough Medical supplies.....");

        }
        else if(x == 3){
            System.out.println("facility is not operational......");
        }
    }

    @Override
    public Boolean resolve(Object found[]) {
        if(!place.getOperationalStatus()) {
            unableToResolve(3);
            return false;
        }
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
