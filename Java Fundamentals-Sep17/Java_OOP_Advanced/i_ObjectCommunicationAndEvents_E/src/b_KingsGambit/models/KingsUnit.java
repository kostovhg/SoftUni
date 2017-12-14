package b_KingsGambit.models;

import b_KingsGambit.contracts.Attackable;
import b_KingsGambit.contracts.KillableUnits;

public abstract class KingsUnit extends Unit implements KillableUnits {

    private Attackable king;

    KingsUnit(String name, Attackable king) {
        super(name);
        this.king = king;
        this.king.addUnits(this);
    }

    public abstract void update();
}
