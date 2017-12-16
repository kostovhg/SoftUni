package cresla.models.modules;

import cresla.interfaces.AbsorbingModule;

public abstract class AbstractAbsorbingModule extends AbstractModule implements AbsorbingModule {

    private int heatAbsorbing;

    AbstractAbsorbingModule(int id, int heatAbsorbing) {
        super(id);
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " Module - " +
                super.getId() + System.lineSeparator() +
                "Heat Absorbing: " + this.heatAbsorbing;
    }
}
