package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class Facility {
    private String name ;
    private int[] location;
    private int capacity;
    private Boolean operationalStatus;
    private Boolean availability;
    private Resources resources;
    private Resources usableRes;
    private Equipment[] equipments;
    protected static final Resources threshold = new Resources(100.0 , 30.0 , 25.0 , 20.0 , 15.0 , 10.0 , 8.0);
    private HumanPersonnel[] personnels;

    public Facility(String name, int[] location, int capacity,Equipment[] equipments, HumanPersonnel[] personnels , Resources resources) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.operationalStatus = true;
        this.availability = true;
        this.equipments = equipments;
        if(personnels.length <= this.capacity)
            this.personnels = personnels;
        //else throw an error
        if(resources.comp(threshold)) {
            this.resources = resources;
            this.usableRes = resources.sub(threshold);
        }
        //else throw an error
    }

    public String getName() {
        return name;
    }

    public int[] getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public Boolean getOperationalStatus() {
        return operationalStatus;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public Equipment[] getEquipments() {
        return equipments;
    }

    public int getEquipmentCount(){
        return equipments.length;
    }

    public HumanPersonnel[] getPersonnels() {
        return personnels;
    }

    public int getHumanCount(){
        return personnels.length;
    }

    public Boolean canAcceptHuman(){
        return personnels.length < capacity;
    }

    public Boolean changeAvailability(){
        this.availability = ! this.availability;
        return true;
    }

    public Boolean changeOperationalStatus(){
        this.operationalStatus = !this.operationalStatus;
        if(!this.operationalStatus){
            this.availability = false;
        }
        return true;
    }

    public Resources facilityConsumeResource(Resources rec){
        if(usableRes.comp(rec) == false){
            Resources rem = rec.sub(usableRes);
            usableRes = usableRes.sub(rec);
            resources = usableRes.add(threshold);
            return rem;
        }
        else{
            usableRes = usableRes.sub(rec);
            resources = usableRes.add(threshold);
            return new Resources(0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0);
        }
    }
}
