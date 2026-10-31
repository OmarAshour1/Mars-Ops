package Agents;


public class Engineer extends HumanPersonnel {

    public Engineer() {
        super();
    }

    public Engineer(String name, int identificationNumber, int[] location, String place, String[] skills) {
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