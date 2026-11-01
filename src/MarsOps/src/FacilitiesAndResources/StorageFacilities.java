package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class StorageFacilities extends Facility {

    public StorageFacilities(String name, int[] location) {
        super(name, location);
        equipments = new Equipment[6];
        equipments[0] = new ExplorationEquipment("ExplorationEquipment");
        equipments[1] = new ExplorationEquipment("ExplorationEquipment");
        equipments[2] = new ScientificEquipment("ScientificEquipment");
        equipments[3] = new ScientificEquipment("ScientificEquipment");
        equipments[4] = new ExplorationEquipment("ExplorationEquipment");
        equipments[5] = new ScientificEquipment("ScientificEquipment");
    }
}
