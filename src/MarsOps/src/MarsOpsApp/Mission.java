package MarsOpsApp;

public abstract class Mission {
    public enum state{
        in_progress,completed,faild
    }
    private int[] Status = new int[2];
    private String Name;
    private facilites facility;

    public Mission(facilites facility, String name, int[] status) {
        this.facility = facility;
        Name = name;
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public facilites getFacility() {
        return facility;
    }

    public int[] getStatus() {
        return Status;
    }
    public abstract boolean startMission(Object [] requirments);

}
