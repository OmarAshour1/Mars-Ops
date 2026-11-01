package FacilitiesAndResources;

public class Resources {
    private double oxygen;
    private double water;
    private double fuel;
    private double food;
    private double batteryPower;
    private double medicalSupplies;
    private double spareParts;

    public Resources(double oxygen, double water, double fuel, double food, double batteryPower, double medicalSupplies, double spareParts) {
        this.oxygen = oxygen;
        this.water = water;
        this.fuel = fuel;
        this.food = food;
        this.batteryPower = batteryPower;
        this.medicalSupplies = medicalSupplies;
        this.spareParts = spareParts;
    }

    public double getOxygen() {
        return oxygen;
    }

    public double getWater() {
        return water;
    }

    public double getFuel() {
        return fuel;
    }

    public double getFood() {
        return food;
    }

    public double getBatteryPower() {
        return batteryPower;
    }

    public double getMedicalSupplies() {
        return medicalSupplies;
    }

    public double getSpareParts() {
        return spareParts;
    }

    public Boolean comp(Resources res){
        if(this.oxygen < res.oxygen)
            return false;

        if(this.water < res.water)
            return false;

        if(this.fuel < res.fuel)
            return false;

        if(this.food < res.food)
            return false;

        if(this.batteryPower < res.batteryPower)
            return false;

        if(this.medicalSupplies < res.medicalSupplies)
            return false;

        if(this.spareParts < res.spareParts)
            return false;

        return true;
    }

    public Resources add(Resources res){
        return new Resources(this.oxygen+res.oxygen
                            ,this.water+res.water
                            ,this.fuel+res.fuel
                            ,this.food + res.food
                            ,this.batteryPower+res.batteryPower
                            ,this.medicalSupplies+res.medicalSupplies
                            ,this.spareParts+res.spareParts);
    }

    public Resources sub(Resources res){
        double oxygen1 , water1 , fuel1 , food1 , batteryPower1 , medicalSupplies1 , spareParts1 ;
        if(this.oxygen < res.oxygen)
            oxygen1 = 0;
        else oxygen1 = this.oxygen-res.oxygen;

        if(this.water < res.water)
            water1 = 0;
        else water1 = this.water-res.water;

        if(this.fuel < res.fuel)
            fuel1 = 0;
        else fuel1 = this.fuel-res.fuel;

        if(this.food < res.food)
             food1 = 0;
        else food1 = this.food-res.food;

        if(this.batteryPower < res.batteryPower)
            batteryPower1 = 0;
        else batteryPower1 = this.batteryPower-res.batteryPower;

        if(this.medicalSupplies < res.medicalSupplies)
            medicalSupplies1 = 0;
        else medicalSupplies1 = this.medicalSupplies-res.medicalSupplies;

        if(this.spareParts < res.spareParts)
            spareParts1= 0;
        else spareParts1 = this.spareParts-res.spareParts;

        return new Resources(oxygen1 , water1 , fuel1 , food1 , batteryPower1 , medicalSupplies1 , spareParts1);

    }

}
