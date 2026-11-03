package Test;

import Colony.ColonyManager;

import java.util.Arrays;
import java.util.Scanner;

public class TestMain {

    private static final int TOTAL_SECONDS = 100;

    // names understood by ColonyManager.applyForAMission
    private static final String[] MISSIONS = {"research", "rescue", "exploration", "repair"};

    // names understood by ColonyManager.Emerge
    private static final String[] EMERGENCIES = {"oxygenleak", "fire", "medicalemergency", "roverbreak"};

    // names understood by ColonyManager.changeEnvironment
    private static final String[] CONDITIONS = {"normal", "dust", "commdisruption"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColonyManager colony = new ColonyManager();

        for (int second = 1; second <= TOTAL_SECONDS; second++) {
            System.out.println("\n===== Second " + second + " / " + TOTAL_SECONDS + " =====");
            System.out.println("1. Mission");
            System.out.println("2. Emergency");
            System.out.println("3. Change environment");
            System.out.println("4. Report");
            System.out.println("5. Continue");

            int choice = readInt(sc, "Choose (1-5): ", 1, 5);

            try {
                switch (choice) {
                    case 1: { // Mission -> ask for the mission name
                        String name = readName(sc, "Enter mission name", MISSIONS);
                        boolean started = colony.applyForAMission(name);
                        System.out.println("Mission '" + name + "' in progress: " + started);
                        break;
                    }
                    case 2: { // Emergency -> choose the emergency name
                        System.out.println("Choose emergency:");
                        for (int i = 0; i < EMERGENCIES.length; i++)
                            System.out.println((i + 1) + ". " + EMERGENCIES[i]);
                        int e = readInt(sc, "Emergency (1-" + EMERGENCIES.length + "): ", 1, EMERGENCIES.length);
                        String name = EMERGENCIES[e - 1];
                        Boolean ok = colony.Emerge(name);
                        System.out.println("Emergency '" + name + "' resolving: " + ok);
                        break;
                    }
                    case 3: { // Change environment -> any condition
                        String cond = readName(sc, "Enter condition", CONDITIONS);
                        colony.changeEnvironment(cond);
                        System.out.println("Environment changed to: " + cond);
                        break;
                    }
                    case 4: // Report
                        colony.colonyReport();
                        break;
                    default: // Continue
                        System.out.println("Continuing...");
                }
            } catch (Exception ex) {
                // a bug inside ColonyManager must not stop the 100-second run
                System.out.println("!! " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
                StackTraceElement[] st = ex.getStackTrace();
                if (st.length > 0) System.out.println("   at " + st[0]);
            }

            colony.advanceTime(1); // one second passes
        }

        System.out.println("\nSimulation finished. Colony time = " + colony.getTime());
    }

    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNextLine()) { System.out.println("\n(no more input)"); System.exit(0); }
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) { }
            System.out.println("Invalid. Enter a number from " + min + " to " + max + ".");
        }
    }

    private static String readName(Scanner sc, String prompt, String[] allowed) {
        while (true) {
            System.out.print(prompt + " " + Arrays.toString(allowed) + ": ");
            if (!sc.hasNextLine()) { System.out.println("\n(no more input)"); System.exit(0); }
            String s = sc.nextLine().trim().toLowerCase();
            for (String a : allowed) if (a.equals(s)) return s;
            System.out.println("Unknown name, try again.");
        }
    }
}