package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class CommunicationStations extends Facility{
    public CommunicationStations(String name, int[] location) {
        super(name, location);
        equipments = new Equipment[6];
        equipments[0] = new ExplorationEquipment("ExplorationEquipment");
        equipments[1] = new ExplorationEquipment("ExplorationEquipment");
        equipments[2] = new ScientificEquipment("ScientificEquipment");
    }
}
