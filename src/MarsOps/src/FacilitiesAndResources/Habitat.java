package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class Habitat extends Facility{
    public Habitat(String name, int[] location, int capacity, Equipment[] equipments, HumanPersonnel[] personnels, Resources resources) {
        super(name, location, capacity, equipments, personnels, resources);
    }
}
