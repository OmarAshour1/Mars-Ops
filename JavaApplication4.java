package MarsOpsApp;

import Colony.ColonyManager;
import Emergencies.Emergency;
import FacilitiesAndResources.Facility;
import FacilitiesAndResources.Resources;
import Agents.HumanPersonnel;
import Agents.RoboticUnit;
import Missions.Mission;

import java.util.Scanner;

/**
 * Console entry point for the MARS-OPS simulator.
 * Class name/package match "main.class" in nbproject/project.properties.
 */
public class JavaApplication4 {

    public static void main(String[] args) {
        ColonyManager colony = new ColonyManager();
        Scanner in = new Scanner(System.in);

        System.out.println("=== MARS-OPS Colony Simulator ===");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> printReport(colony);
                    case "2" -> startMission(colony, in);
                    case "3" -> triggerEmergency(colony, in);
                    case "4" -> changeEnvironment(colony, in);
                    case "5" -> advanceTime(colony, in);
                    case "0" -> running = false;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                // The simulation core has some unguarded paths; don't crash the session.
                System.out.println("Action failed: " + e);
            }
        }
        System.out.println("Simulation ended at t=" + colony.getTime() + ". Goodbye.");
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1) Colony report");
        System.out.println("2) Start a mission");
        System.out.println("3) Trigger an emergency");
        System.out.println("4) Change environment");
        System.out.println("5) Advance time");
        System.out.println("0) Exit");
        System.out.print("> ");
    }

    private static void startMission(ColonyManager colony, Scanner in) {
        System.out.print("Mission (research / rescue / exploration / repair): ");
        String type = in.nextLine().trim().toLowerCase();
        if (!type.matches("research|rescue|exploration|repair")) {
            System.out.println("Unknown mission type.");
            return;
        }
        // exploration/repair prompt for extra input via ColonyManager's own Scanner
        colony.applyForAMission(type);
    }

    private static void triggerEmergency(ColonyManager colony, Scanner in) {
        System.out.print("Emergency (oxygenleak / fire / medicalemergency / roverbreak): ");
        String type = in.nextLine().trim().toLowerCase();
        if (!type.matches("oxygenleak|fire|medicalemergency|roverbreak")) {
            System.out.println("Unknown emergency type.");
            return;
        }
        colony.Emerge(type);
    }

    private static void changeEnvironment(ColonyManager colony, Scanner in) {
        System.out.print("Condition (normal / dust / comm): ");
        String cond = in.nextLine().trim().toLowerCase();
        colony.changeEnvironment(cond.equals("comm") ? "comm" : cond);
        System.out.println("Environment set to: " + cond);
    }

    private static void advanceTime(ColonyManager colony, Scanner in) {
        System.out.print("Ticks to advance: ");
        int ticks;
        try {
            ticks = Integer.parseInt(in.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number.");
            return;
        }
        for (int i = 0; i < ticks; i++) {
            colony.advanceTime(1);
        }
        System.out.println("Time is now t=" + colony.getTime());
    }

    private static void printReport(ColonyManager colony) {
        System.out.println("\n=== Colony Report (t=" + colony.getTime() + ") ===");
        printResources("Total resources ", colony.getColonyWidRes());
        printResources("Usable resources", colony.getUsableResources());

        Mission m = colony.getCurrentMission();
        System.out.println("Mission: " + (m == null ? "none"
                : m.getName() + " [" + m.getStatus() + ", timer=" + m.getTimer() + "]"));

        System.out.println("Emergencies:");
        boolean any = false;
        for (Emergency e : colony.getEmergencies()) {
            if (e != null) {
                any = true;
                System.out.println("  " + e.getName() + " [" + e.getStatus() + ", timer=" + e.getTimer() + "]");
            }
        }
        if (!any) System.out.println("  none");

        System.out.println("Facilities:");
        for (Facility f : colony.getFacilities()) {
            System.out.println("  " + f.getName() + " operational=" + f.getOperationalStatus()
                    + " available=" + f.getAvailability());
        }
        System.out.println("Personnel:");
        for (HumanPersonnel p : colony.getPeople()) {
            System.out.println("  " + p.getName() + " (" + p.getType() + ") healthy=" + p.getHealthStatus()
                    + " available=" + p.getAvailability() + " fatigue=" + p.getFatigueLevel());
        }
        System.out.println("Robots:");
        for (RoboticUnit r : colony.getRobots()) {
            System.out.println("  " + r);
        }
    }

    private static void printResources(String label, Resources r) {
        System.out.printf("%s: O2=%.0f water=%.0f fuel=%.0f food=%.0f battery=%.0f medical=%.0f parts=%.0f%n",
                label, r.getOxygen(), r.getWater(), r.getFuel(), r.getFood(),
                r.getBatteryPower(), r.getMedicalSupplies(), r.getSpareParts());
    }
}
