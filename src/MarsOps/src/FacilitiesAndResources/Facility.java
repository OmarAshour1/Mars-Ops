package FacilitiesAndResources;

import Agents.HumanPersonnel;

public class Facility {
    private String name ;
    private int[] location;
    private static final int capacity = 10;
    private Boolean operationalStatus;
    private Boolean availability;
    private Resources resources;
    private Resources usableRes;
    protected Equipment[] equipments;
    protected static final Resources threshold = new Resources(100.0 , 30.0 , 25.0 , 20.0 , 15.0 , 10.0 , 8.0);
    private HumanPersonnel[] personnels;
    private int numberOfPersonnels;

    public Facility(String name, int[] location) {
        this.name = name;
        this.location = location;
        this.operationalStatus = true;
        this.availability = true;
        this.personnels = new HumanPersonnel[capacity];
        this.numberOfPersonnels = 0;
        this.resources = new Resources(900,100,200,200,1000,500,500);
        this.usableRes = resources.sub(threshold);

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

    public Boolean addPersonnel(HumanPersonnel h){
        if(numberOfPersonnels < capacity) {
            personnels[numberOfPersonnels] = h;
            numberOfPersonnels++;
            return true;
        }
        System.out.println("the Capacity is full");
        return false;
    }


    public HumanPersonnel[] getPersonnels() {
        return personnels;
    }

    public int getHumanCount(){
        return numberOfPersonnels;
    }

    public Boolean canAcceptHuman(){
        return numberOfPersonnels < capacity;
    }

    public Resources getUsableRes() {
        return usableRes;
    }

    public Resources getResources() {
        return resources;
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
