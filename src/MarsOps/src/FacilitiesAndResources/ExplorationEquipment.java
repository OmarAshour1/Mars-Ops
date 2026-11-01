package FacilitiesAndResources;

public class ExplorationEquipment extends Equipment {
 
    public static int count;
 
    public ExplorationEquipment(String name) {
        super(name);
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative.");
        }
        count++;
    }
 
    public int getCount() { return count; }

}
