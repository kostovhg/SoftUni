package SystemSplit.entities;

import static SystemSplit.utilities.Constants.POWER_TYPE;

public class PowerHardware extends Hardware {

    public PowerHardware(String name, int capacity, int memory) {
        super(name);
        super.setType(POWER_TYPE);
        this.setCapacity(capacity - (capacity * 75) / 100);
        this.setMemory(memory + (memory * 75) / 100);
    }
}
