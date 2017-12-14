package b_KingsGambit.models;

import b_KingsGambit.contracts.Attackable;

public class Footman extends KingsUnit {

    public Footman(String name, Attackable king) {
        super(name, king);
    }

    @Override
    public void update() {
        System.out.println("Footman " + this.name + " is panicking!");
    }
}
