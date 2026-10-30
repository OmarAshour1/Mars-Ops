package MarsOpsApp;

public class OxygenLeak extends Emergency {
    private final double amountLeak = -20;

    public OxygenLeak(String name, int[] location, Facility place) {
        //if place is not habbitat throw an error
        Boolean ret = place.changeResources("oxygen" ,amountLeak);
        if(ret)
            if(place.getAvailability())
                place.changeAvailability();
            if(place.getOperational())
                place.changeOperationalStatus();
            super(name, location, place);
        
        // else throw an error
    }

    public double getAmountLeak() {
        return -1 * amountLeak;
    }
    
    @Override
    public Object[] requirments(){
        Object req[] = new Object[12];
        req[0] = "engineer"; // human
        req[1] = "leakage"; // skill
        req[2] = "repairunit"; //robot
        req[3] = 0;//oxygen
        req[4] = 0;//water
        req[5] = 0;//food
        req[6] = 0;//fuel
        req[7] = 0;//battery power
        req[8] = 0;//medical supplies
        req[9] = 4.0;//spare parts
        req[10] = null; //exploration equipment
        req[11] = null; //scientific equipment
        
        return req;
    }

    @Override
    public Boolean resolve() {
        
    }
    
    
    
}
