package Agents;


import FacilitiesAndResources.Facility;

public class MedicalOfficer extends HumanPersonnel {

    public MedicalOfficer() {
        super();
    }

    public MedicalOfficer(String name, int identificationNumber, int[] location, Facility place, String[] skills) {
        super(name, identificationNumber, location, place, skills);
    }

    public boolean respondMedicalEmergences() {
        if (getHealthStatus() && getAvailability()) {
            increaseFatigueLevel(1);
            return true;
        }
        return false;
    }
}