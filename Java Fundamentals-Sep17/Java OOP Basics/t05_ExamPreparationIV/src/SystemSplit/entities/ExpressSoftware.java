package SystemSplit.entities;

import static SystemSplit.utilities.Constants.EXPRESS_TYPE;

public class ExpressSoftware extends Software {

    public ExpressSoftware(String name, int capacityConsumption, int memoryConsumption) {
        super(name);
        super.setType(EXPRESS_TYPE);
        this.setCapacityConsumption(capacityConsumption);
        this.setMemoryConsumption(memoryConsumption * 2);
    }
}
