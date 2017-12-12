package lab.p04_recharge.models;

import lab.p04_recharge.contracts.Rechargeable;

public class RechargeStation {

    public void recharge(Rechargeable rechargeable) {
        rechargeable.recharge();
    }
}
