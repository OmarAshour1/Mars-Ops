package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class Habitat extends Facility{
    public Habitat(String name, int[] location) {
        super(name, location);
        equipments = new Equipment[3];
        equipments[0] = new ExplorationEquipment("ExplorationEquipment");
        equipments[1] = new ExplorationEquipment("ExplorationEquipment");

    }
}
