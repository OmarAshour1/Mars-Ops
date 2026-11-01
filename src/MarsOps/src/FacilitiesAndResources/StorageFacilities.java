package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class StorageFacilities extends Facility {

    public StorageFacilities(String name, int[] location, int capacity, Equipment[] equipments, HumanPersonnel[] personnels, Resources resources) {
        super(name, location, capacity, equipments, personnels, resources);
    }
}
