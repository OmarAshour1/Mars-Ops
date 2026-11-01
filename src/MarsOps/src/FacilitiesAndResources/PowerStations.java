package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class PowerStations extends Facility{
    public PowerStations(String name, int[] location, int capacity, Equipment[] equipments, HumanPersonnel[] personnels, Resources resources) {
        super(name, location, capacity, equipments, personnels, resources);
    }
}
