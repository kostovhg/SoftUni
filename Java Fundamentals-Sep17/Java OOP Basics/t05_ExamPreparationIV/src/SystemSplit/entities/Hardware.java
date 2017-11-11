package SystemSplit.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static SystemSplit.utilities.Constants.EXPRESS_TYPE;
import static SystemSplit.utilities.Constants.LIGHT_TYPE;

public abstract class Hardware {

    private final String name;
    private String type;
    private int capacity;
    private int takenCapacity;
    private int memory;
    private int takenMemory;
    private final List<Software> software;

    Hardware(String name) {
        this.name = name;
        this.setCapacity(capacity);
        this.setMemory(memory);
        this.takenCapacity = 0;
        this.takenMemory = 0;
        this.software = new ArrayList<>();
    }

    public void addSoftware(Software sf) {
        if (isOkForInsertion(sf)) {
            this.software.add(sf);
            this.takenMemory += sf.getMemoryConsumption();
            this.takenCapacity += sf.getCapacityConsumption();
        }

    }

    public void removeSoftware(String name) {
        this.software.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst().ifPresent(s ->
        {
            this.takenCapacity -= s.getCapacityConsumption();
            this.takenMemory -= s.getMemoryConsumption();
            this.software.remove(s);
        });
    }

    void setType(String type) {
        this.type = type;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    void setMemory(int memory) {
        this.memory = memory;
    }

    public List<Software> getSoftware() {
        return Collections.unmodifiableList(this.software);
    }

    private String getName() {
        return name;
    }

    private boolean isOkForInsertion(Software sf) {
        return !software.contains(sf) &&
                sf.getCapacityConsumption() <= this.capacity - takenCapacity &&
                sf.getMemoryConsumption() <= this.memory - takenMemory;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTakenCapacity() {
        return takenCapacity;
    }

    public int getMemory() {
        return memory;
    }

    public int getTakenMemory() {
        return takenMemory;
    }

    public String getType() {
        return type;
    }

    public long getCountOfSoftwareType(String type) {
        return this.software.stream()
                .filter(s -> s.getType().equals(type))
                .collect(Collectors.toList()).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hardware Component - ").append(this.getName()).append(System.lineSeparator());
        sb.append("Express Software Components - ");
        sb.append(getCountOfSoftwareType(EXPRESS_TYPE));
        sb.append(System.lineSeparator());
        sb.append("Light Software Components - ");
        sb.append(getCountOfSoftwareType(LIGHT_TYPE));
        sb.append(System.lineSeparator());
        sb.append("Memory Usage: ").append(this.getTakenMemory());
        sb.append(" / ").append(this.getMemory());
        sb.append(System.lineSeparator());
        sb.append("Capacity Usage: ").append(this.getTakenCapacity());
        sb.append(" / ").append(this.getCapacity());
        sb.append(System.lineSeparator());
        sb.append("Type: ").append(this.getType());
        sb.append(System.lineSeparator());
        sb.append("Software Components: ").append(getSoftwareElements());
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    private String getSoftwareElements() {
        List<String> software = new ArrayList<>();
        for (Software sf :
                this.getSoftware()) {
            software.add(sf.getName());
        }
        if (software.isEmpty()) {
            return "None";
        } else {
            return String.join(", ", software);
        }
    }
}
