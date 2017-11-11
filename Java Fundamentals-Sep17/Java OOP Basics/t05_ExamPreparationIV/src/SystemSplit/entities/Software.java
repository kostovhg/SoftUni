package SystemSplit.entities;

public abstract class Software {

    private final String name;
    private String type;
    private int capacityConsumption;
    private int memoryConsumption;

    Software(String name) {
        this.name = name;
    }

    void setType(String type) {
        this.type = type;
    }

    void setCapacityConsumption(int capacityConsumption) {
        this.capacityConsumption = capacityConsumption;
    }

    void setMemoryConsumption(int memoryConsumption) {
        this.memoryConsumption = memoryConsumption;
    }

    int getCapacityConsumption() {
        return capacityConsumption;
    }

    int getMemoryConsumption() {
        return memoryConsumption;
    }

    String getName() {
        return name;
    }

    String getType() {
        return type;
    }
}
