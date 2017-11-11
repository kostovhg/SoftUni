package SystemSplit.entities;

import static SystemSplit.utilities.Constants.LIGHT_TYPE;

public class LightSoftware extends Software {

    public LightSoftware(String name, int capacityConsumption, int memoryConsumption) {
        super(name);
        super.setType(LIGHT_TYPE);
        this.setCapacityConsumption(capacityConsumption + (capacityConsumption * 50) / 100);
        this.setMemoryConsumption(memoryConsumption - (memoryConsumption * 50) / 100);
    }
}
