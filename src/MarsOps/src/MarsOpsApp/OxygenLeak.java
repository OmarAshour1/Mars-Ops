package MarsOpsApp;

public class OxygenLeak extends Emergency {
    private static final double amountLeak = -20;
    private static final Resources resource = new Resources(0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 4.0);

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
    public OxygenLeak(String name, Facility place) {
        //if place is not habbitat throw an error
        Boolean ret = place.changeResources("oxygen" ,amountLeak);
        if(ret)
            if(place.getAvailability())
                place.changeAvailability();
            if(place.getOperational())
                place.changeOperationalStatus();
            super(name, place.getLocation() ,place);
        // else throw an error
    }

    public double getAmountLeak() {
        return -1 * amountLeak;
    }
    
    @Override
    public Object[] requirments(){
        resource =getPlace().getResources().sub(resource); 
        Object req[] = new Object[12];
        req[0] = "engineer"; // human
        req[1] = "leakage"; // skill
        req[2] = "repairunit"; //robot
        req[3] = resource; //resources
        req[4] = null; //exploration equipment
        req[5] = null; //scientific equipment
        req[6] = resource.checkResources();
        return req;
    }
    
    private void unableToResolve(int x){
        if(x == 0){
            System.out.println("We can't find any available or even capable Person.....");
        }
        else if(x == 1){
            System.out.println("We can't find any available or even capable Robot.....");           
        }
        else if(x == 2){
            System.out.println("No enough spare parts.....");

        }
    }
    
    @Override
    public Boolean resolve(Object found[]) {
        if(found[0] == null){
            unableToResolve(0);
            return false;
        }
        if(found[1] == null){
            unableToResolve(1);
            return false;
        }
//        if((Boolean)found[2] == false){
//            unableToResolve(2);
//            return false;
//        }
        assignHuman(found[0]);
        assignRobot(found[1]);
        //consume(found[2]);
        System.out.println("emergency is being resolved...");
        setEmergencyStatus(EmergencyStatus.Resolving);
        
        return true;
    }
    
    
    
    
}
