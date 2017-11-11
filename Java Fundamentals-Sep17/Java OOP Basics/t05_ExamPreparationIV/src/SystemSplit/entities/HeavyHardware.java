package SystemSplit.entities;

import static SystemSplit.utilities.Constants.HEAVY_TYPE;

public class HeavyHardware extends Hardware {

    public HeavyHardware(String name, int capacity, int memory) {
        super(name);
        super.setType(HEAVY_TYPE);
        super.setCapacity(capacity * 2);
        super.setMemory(memory - (memory * 25) / 100);
    }
}
