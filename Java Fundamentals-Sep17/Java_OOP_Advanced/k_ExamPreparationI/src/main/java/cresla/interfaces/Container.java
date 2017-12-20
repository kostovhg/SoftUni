package cresla.interfaces;

public interface Container {

    long getTotalEnergyOutput();

    long getTotalHeatAbsorbing();

    void addEnergyModule(EnergyModule energyModule);

    void addAbsorbingModule(AbsorbingModule absorbingModule);
}
