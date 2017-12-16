package cresla.interfaces;

public interface Reactor extends Identifiable {

    long getTotalEnergyOutput();

    long getTotalHeatAbsorbing();

    int getModuleCount() throws ClassNotFoundException;

    void addEnergyModule(EnergyModule energyModule);

    void addAbsorbingModule(AbsorbingModule absorbingModule);
}
