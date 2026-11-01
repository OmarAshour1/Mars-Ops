package Agents;

import FacilitiesAndResources.Facility;

public class Engineer extends HumanPersonnel {

    public Engineer() {
        super();
    }

    public Engineer(String name, int identificationNumber, int[] location, Facility  place, String[] skills) {
        super(name, identificationNumber, location, place, skills);
    }
    public boolean repairDamageEquipment() {
        if (getHealthStatus() && getAvailability()) {
            increaseFatigueLevel(1);
            return true;
        }
        return false;
    }
}