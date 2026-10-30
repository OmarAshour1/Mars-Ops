package MarsOpsApp;

public abstract class Mission {
    public enum MissionStatus {
        Un_ASSIGNED,
        IN_PROGRESS,
        COMPLETED,
        FAILED
    }
    private MissionStatus status ;
    private String name;
    private facilites facility;
    private int [] location =new int[2] ;

    public Mission(facilites facility, String name) {
        this.facility = facility;
        this.name = name;
        status = MissionStatus.Un_ASSIGNED;
    }

    public String getName() {
        return Name;
    }

    public facilites getFacility() {
        return facility;
    }

    public MissionStatus getStatus() {
        return this.Status;
    }
    public abstract boolean startMission(Object [] requirments);
    public boolean endMission(){
        this.status=MissionStatus.COMPLETED;
        return true;
    }

}
