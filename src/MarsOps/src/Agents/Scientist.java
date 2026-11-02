package Agents;

import FacilitiesAndResources.Facility;

public class Scientist extends HumanPersonnel {

    private static final String type="scientist";

    public Scientist() {
        super();
    }

    public Scientist(String name, int identificationNumber, int[] location, Facility place, String[] skills) {
        super(name, identificationNumber, location, place, skills,type);
    }

    public boolean performScientistResearch() {
        if (getHealthStatus() && getAvailability()) {
            increaseFatigueLevel(1);
            return true;
        }
        return false;
    }
}

