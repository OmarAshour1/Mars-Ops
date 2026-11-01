package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class CommunicationStations extends Facility{
    public CommunicationStations(String name, int[] location, int capacity, Equipment[] equipments, HumanPersonnel[] personnels, Resources resources) {
        super(name, location, capacity, equipments, personnels, resources);
    }
}
