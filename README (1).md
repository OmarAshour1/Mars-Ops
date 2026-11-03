# MARS-OPS

A Java console simulator of a Mars colony. It models habitats, labs, power/storage/comm facilities, human personnel, robotic units, shared resources, missions, and emergencies, all driven by a discrete time step.

## Project structure

```
Mars-Ops/
├── docs/uml/                  # UML diagrams (source/ for editable files)
└── src/MarsOps/               # NetBeans / Ant project
    └── src/
        ├── MarsOpsApp/        # Entry point (JavaApplication4)
        ├── Colony/            # ColonyManager, Environment
        ├── Agents/            # HumanPersonnel (Engineer, Scientist, MedicalOfficer),
        │                      # RoboticUnit (Drone, ExplorationRover, RepairRobot)
        ├── FacilitiesAndResources/  # Facility types, Equipment, Resources
        ├── Missions/          # Research, Rescue, Exploration, Repair
        └── Emergencies/       # OxygenLeak, Fire, MedicalEmergency, RoverBreakdown
```

## Requirements

- JDK 25 (`javac.source`/`javac.target` in `nbproject/project.properties`; the code itself needs only Java 14+ for the `switch` syntax in `main`)
- Apache NetBeans (optional) or Ant

## Build & run

**NetBeans:** open `src/MarsOps`, then Run. Main class is `MarsOpsApp.JavaApplication4`.

**Ant:**
```bash
cd src/MarsOps
ant jar
java -cp build/classes MarsOpsApp.JavaApplication4
```

**Plain javac:**
```bash
cd src/MarsOps/src
javac -d ../out $(find . -name "*.java")
java -cp ../out MarsOpsApp.JavaApplication4
```

## Using the simulator

The program shows a menu:

| Option | Action |
|--------|--------|
| 1 | Colony report (resources, mission, emergencies, facilities, people, robots) |
| 2 | Start a mission: `research`, `rescue`, `exploration`, `repair` |
| 3 | Trigger an emergency: `oxygenleak`, `fire`, `medicalemergency`, `roverbreak` |
| 4 | Change environment: `normal`, `dust`, `comm` (communication disruption) |
| 5 | Advance time by N ticks |
| 0 | Exit |

Only one mission can run at a time, and up to 5 emergencies can be active. Missions and emergencies resolve after 3 ticks if their personnel, robot and facility stay operational.

- **Dust** aborts exploration missions.
- **Comm disruption** aborts rescue missions.
- A **fire** doubles the laboratory's oxygen consumption until resolved.
- Facilities that can no longer cover per-tick consumption go non-operational.

## Emergency protocols

| Emergency | Human responder | Robot responder | Resources | Notes |
|-----------|-----------------|-----------------|-----------|-------|
| Oxygen Leak | Engineer | Repair unit | 4 repair units | Strikes one of the habitats |
| Fire | Medical Officer | Repair unit | 10 medical supplies | Disables the lab and its equipment |
| Rover Breakdown | Engineer | Repair robot | 2 spare parts | Needs a rover on a mission; may spawn a repair mission |
| Medical Emergency | Medical Officer | Rover | 5 medical supplies | Check the facility is operational before dispatch |

## Missions

| Mission | Human | Robot | Resources |
|---------|-------|-------|-----------|
| Research | Scientist (scientific skill) | Repair robot | water 3, fuel 2, food 2, battery 4, parts 4 |
| Rescue | Any (rescue skill) | Drone | food 2, battery 4, parts 4 |
| Exploration | Exploration skill | Rover | see `Exploration.java` |
| Repair | Engineer (repairing skill) | Repair robot | see `Repair.java` |

## Known issues

These come from the existing simulation code, not the entry point:

- Responder type strings are inconsistent (e.g. `Fire` asks for `"MedicalOfficer"` while personnel use `"medicalofficer"`; `Fire` wants robot `"repairunit"` but no robot has that type), so some emergencies can't find a responder.
- `RoboticUnit` never stores `typeRobot` from its constructor, so `getTypeRobot()` is `null` and robot matching fails.
- Both `PowerStation` facilities share the name `"PowerStation1"`.
- `ColonyManager` creates its own `Scanner(System.in)` for exploration/repair prompts.

## Contributing

1. Create a branch from `main`.
2. Keep UML in `docs/uml/source`.
3. Open a pull request.
