package Missions;
import Agents.HumanPersonnel;
import Agents.RoboticUnit;
import FacilitiesAndResources.Facility;
public abstract class Mission {
    public enum MissionStatus {
        UN_ASSIGNED,
        IN_PROGRESS,
        COMPLETED,
        FAILED
    }
    protected RoboticUnit robot;
    private MissionStatus status ;
    private String name;
    private Facility facility;
    private int [] location =new int[2] ;
    protected HumanPersonnel personnel;
    private int timer;

    public Mission(Facility facility, String name) {
        this.facility = facility;
        this.name = name;
        status = MissionStatus.UN_ASSIGNED;
        this.timer = 0;
    }

    public void increaseTimer(int t){
        this.timer += t;
    }

    public int getTimer() {
        return timer;
    }

    public String getName() {
        return name;
    }

    public Facility getFacility() {
        return facility;
    }

    public MissionStatus getStatus() {
        return this.status;
    }

    protected boolean missionFaild(int x){
        if (x==-2)
            System.out.println("Available Dron isn't found");
        else if(x==-1)
            System.out.println("Unavailable Human ");
        else if(x==0)
            System.out.println("the Scientist with the  scientific skill isn't found ");
        else if (x==1)
            System.out.println("the Engineer isn't found ");
        else if (x==2)
            System.out.println("the Rover isn't found ");
        else if (x==3)
            System.out.println("the Repair Robot isn't found ");
        else if (x==4)
            System.out.println(" Insufficient Resources ");
        else if (x==5)
            System.out.println("the Exploration equipment isn't found ");
        else
            return false;
        return true;
    }
    public abstract boolean startMission(Object [] requirments);

    public boolean endMission(){
        this.personnel.increaseFatigueLevel(1);
        this.personnel.changeAvailability();
        this.robot.changeAvailability();
        this.robot.consumeBattery(5);
        this.status=MissionStatus.COMPLETED;
        return true;
    }
    public abstract Object[] requirments();
    public boolean changeStatus(MissionStatus s){
        this.status=s;
        return true;
    }

    public boolean stopMission(){
        System.out.println("Mission Failed");
        this.status=MissionStatus.FAILED;
        return true;
    }

    public RoboticUnit getRobot() {
        return robot;
    }

    public HumanPersonnel getPerson() {
        return personnel;
    }
}
