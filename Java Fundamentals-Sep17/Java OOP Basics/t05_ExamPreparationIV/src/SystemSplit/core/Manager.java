package SystemSplit.core;

import SystemSplit.entities.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static SystemSplit.utilities.Constants.*;

public class Manager {
    private final Map<String, Hardware> hardware;
    private final Map<String, Hardware> dump;

    public Manager() {
        this.hardware = new LinkedHashMap<>();
        this.dump = new HashMap<>();
    }

    public void registerPowerHardware(String name, int capacity, int memory) {
        this.hardware.put(name, new PowerHardware(name, capacity, memory));
    }

    public void registerHeavyHardware(String name, int capacity, int memory) {
        this.hardware.put(name, new HeavyHardware(name, capacity, memory));
    }


    public void registerExpressSoftware(String hardwareComponentName, String name, int capacity, int memory) {
        if (hardware.containsKey(hardwareComponentName)) {
            Software software = new ExpressSoftware(name, capacity, memory);
            this.hardware.get(hardwareComponentName).addSoftware(software);
        }
    }

    public void registerLightSoftware(String hardwareComponentName, String name, int capacity, int memory) {
        if (hardware.containsKey(hardwareComponentName)) {
            Software software = new LightSoftware(name, capacity, memory);
            this.hardware.get(hardwareComponentName).addSoftware(software);
        }
    }

    public void releaseSoftwareComponent(String hardwareComponentName, String softwareComponentName) {
        this.hardware.get(hardwareComponentName).removeSoftware(softwareComponentName);
    }

    public void dump(String hardwareComponentName) {
        if (this.hardware.containsKey(hardwareComponentName)) {
            Hardware hardware = this.hardware.get(hardwareComponentName);
            dump.putIfAbsent(hardwareComponentName, hardware);
            this.hardware.remove(hardwareComponentName);
        }
    }

    public void restore(String hardwareComponentName) {
        if (this.dump.containsKey(hardwareComponentName)) {
            Hardware hardware = this.dump.get(hardwareComponentName);
            this.hardware.putIfAbsent(hardwareComponentName, hardware);
            this.dump.remove(hardwareComponentName);
        }
    }

    public void destroy(String hardwareComponentName) {
        if (this.dump.containsKey(hardwareComponentName)) {
            this.dump.remove(hardwareComponentName);
        }
    }

    public String dumpAnalyze() {
        StringBuilder sb = new StringBuilder("Dump Analysis").append(System.lineSeparator());
        int totalDumpedMemory = dump.values().stream().mapToInt(Hardware::getTakenMemory).sum();
        long totalDumpedCapacity = dump.values().stream().mapToLong(Hardware::getTakenCapacity).sum();

        sb.append("Power Hardware Components: ").append(getCountOfType(POWER_TYPE));
        sb.append(System.lineSeparator());
        sb.append("Heavy Hardware Components: ").append(getCountOfType(HEAVY_TYPE));
        sb.append(System.lineSeparator());
        sb.append("Express Software Components: ").append(getCountOfType(EXPRESS_TYPE));
        sb.append(System.lineSeparator());
        sb.append("Light Software Components: ").append(getCountOfType(LIGHT_TYPE));
        sb.append(System.lineSeparator());
        sb.append("Total Dumped Memory: ").append(totalDumpedMemory);
        sb.append(System.lineSeparator());
        sb.append("Total Dumped Capacity: ").append(totalDumpedCapacity);

        return sb.toString();
    }

    private long getCountOfType(String type) {
        switch (type) {
            case POWER_TYPE:
            case HEAVY_TYPE:
                return this.dump.values().stream().filter(h -> h.getType().equals(type)).count();
            case EXPRESS_TYPE:
            case LIGHT_TYPE:
                return this.dump.values().stream().mapToLong(h -> h.getCountOfSoftwareType(type)).sum();
            default:
                return 0;
        }

    }

    public String analyze() {
        StringBuilder sb = new StringBuilder();
        sb.append("System Analysis").append(System.lineSeparator());
        sb.append("Hardware Components: ");
        sb.append(this.hardware.size()).append(System.lineSeparator());
        sb.append("Software Components: ");
        sb.append(getSoftwareComponentsCount()).append(System.lineSeparator());
        sb.append("Total Operational Memory: ");
        sb.append(getTotalOperationalMemoryInUse()).append(" / ")
                .append(getMaximumMemory()).append(System.lineSeparator());
        sb.append("Total Capacity Taken: ").append(getTotalCapacityTaken())
                .append(" / ").append(getMaximumCapacity());
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Hardware> hardwares = this.hardware.values().stream()
                .sorted((h1, h2) -> h2.getType().compareTo(h1.getType()))
                .collect(Collectors.toList());
        for (Hardware hardware : hardwares) {
            sb.append(hardware.toString());
        }
        return sb.toString();
    }

    private int getSoftwareComponentsCount() {
        /*
        final int[] count = {0};
        this.hardware.values().forEach(h -> count[0] += h.getSoftwareCount());
        return count[0];
        */
        return this.hardware.values().stream()
                .flatMap(h -> h.getSoftware().stream())
                .collect(Collectors.toList()).size();
    }

    private long getTotalOperationalMemoryInUse() {
        return this.hardware.values().stream()
                .mapToLong(Hardware::getTakenMemory).sum();
    }

    private long getMaximumMemory() {
        return this.hardware.values().stream()
                .mapToLong(Hardware::getMemory).sum();
    }

    private long getTotalCapacityTaken() {
        return this.hardware.values().stream()
                .mapToLong(Hardware::getTakenCapacity).sum();
    }

    private long getMaximumCapacity() {
        return this.hardware.values().stream()
                .mapToLong(Hardware::getCapacity).sum();
    }
}

/*
Dump(hardwareComponentName)
oRemoves from The System the Hardware component with the given name, and throws it into The Dump, along with all of its Software components.
oDumped units do NOT take any memory or capacity on The System.
oIn case there is no component with the given name in The System, the command should do nothing.
Restore(hardwareComponentName)
oRestores the given Hardware component, from The Dump, to The System.
oIn case there is NO such component in The Dump, the command should do nothing.
Destroy(hardwareComponentName)
oRemoves the given Hardware component from The Dump. After this action the component should no longer exist.
oIn case there is NO such component in The Dump, the command should do nothing.
DumpAnalyze()
oShows statistics about the whole Dump in the following format:
“Dump Analysis
  Power Hardware Components: {countOfPowerHardwareComponents}
  Heavy Hardware Components: {countOfHeavyHardwareComponents}
  Express Software Components: {countOfExpressSoftwareComponents}
  Light Software Components: {countOfLightSoftwareComponents}
  Total Dumped Memory: {totalDumpedMemory}
  Total Dumped Capacity: {totalDumpedCapacity}”
oThe dumped memory, capacity, and is calculated from all the components, currently in The Dump.
 */
