package Agents;

public class Scientist extends HumanPersonnel {

    public Scientist() {
        super();
    }

    public Scientist(String name, int identificationNumber, int[] location, String place, String[] skills) {
        super(name, identificationNumber, location, place, skills);
    }

    public boolean performScientistResearch() {
        if (getHealthStatus() && getAvailability()) {
            increaseFatigueLevel(1);
            return true;
        }
        return false;
    }
}

