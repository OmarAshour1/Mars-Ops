package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class Laboratories extends Facility{
    public Laboratories(String name, int[] location) {
        super(name, location);
        equipments = new Equipment[3];
        equipments[0] = new ScientificEquipment("ScientificEquipment");
        equipments[2] = new ScientificEquipment("ScientificEquipment");
        equipments[1] = new ExplorationEquipment("ExplorationEquipment");
    }

}
