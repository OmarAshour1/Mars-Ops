package FacilitiesAndResources;

public class Equipment {
    private String name;
    private boolean availability;
    private boolean status;

    public Equipment(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
        this.availability = true;
        this.status = true;
    }

    public String getName() { return name; }
    public boolean getAvailability() { return availability; }
    public boolean getStatus() { return status; }

    public boolean changeAvailability() {
        if (!status) return false;
        availability = !availability;
        return true;
    }

    public boolean changeStatus() {
        status = !status;
        if (!status) availability = false;
        return status;
    }

    public String toString() {
        return name + " [working=" + status + ", available=" + availability + "]";
    }
}
