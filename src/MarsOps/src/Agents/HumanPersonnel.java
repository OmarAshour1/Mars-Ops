package Agents;

public class HumanPersonnel {
    private String name;
    private int identificationNumber;
    private boolean healthStatus;
    private int[] location;
    private String place;
    private String[] skills;
    private int fatigueLevel;
    private boolean availability;

    // Default Constructor
    public HumanPersonnel() {
        this.name = "Unknown";
        this.identificationNumber = 0;
        this.healthStatus = true;
        this.location = new int[]{0, 0};
        this.place = null;
        this.skills = new String[0];
        this.fatigueLevel = 0;
        this.availability = true;
    }

    // Parameterized Constructor
    public HumanPersonnel(String name, int identificationNumber, int[] location, String place, String[] skills) {
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.healthStatus = true;
        this.location = location;
        this.place = place;
        this.skills = skills;
        this.fatigueLevel = 0;
        this.availability = true;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public boolean getHealthStatus() {
        return healthStatus;
    }

    public int[] getCurrentLocation() {
        return location;
    }

    public String getPlace() {
        return place;
    }

    public String[] getSkills() {
        return skills;
    }

    public int getFatigueLevel() {
        return fatigueLevel;
    }

    public boolean getAvailability() {
        return availability;
    }

    public Boolean canPerform(Object mission) {
        return this.availability && this.healthStatus;
    }

    public Boolean canResolve(Object emergency) {
        return this.availability && this.healthStatus;
    }

    // Protected setters/modifiers
    protected boolean setCurrentLocation(int[] location, String place) {
        if (location == null || location.length != 2) {
            return false;
        }
        this.location = location;
        this.place = place; // Allows null for surface of Mars
        return true;
    }

    protected boolean changeHealthStatus(boolean status) {
        this.healthStatus = status;
        return true;
    }

    protected boolean increaseFatigueLevel(int level) {
        if (level < 0) return false;
        this.fatigueLevel = Math.min(5, this.fatigueLevel + level);
        return true;
    }

    protected boolean changeAvailability() {
        this.availability = !this.availability;
        return true;
    }
}