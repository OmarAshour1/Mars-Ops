package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class Laboratories extends Facility{
    public Laboratories(String name, int[] location, int capacity, Equipment[] equipments, HumanPersonnel[] personnels, Resources resources) {
        super(name, location, capacity, equipments, personnels, resources);
    }
}
