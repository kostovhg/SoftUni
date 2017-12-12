package lab.p04_recharge.adapters;

import lab.p04_recharge.contracts.Rechargeable;
import lab.p04_recharge.outsideLibrary.Robot;

public class RobotAdapter extends Robot implements Rechargeable {

    public RobotAdapter(String id, int capacity) {
        super(id, capacity);
    }

    @Override
    public void recharge() {

    }
}
