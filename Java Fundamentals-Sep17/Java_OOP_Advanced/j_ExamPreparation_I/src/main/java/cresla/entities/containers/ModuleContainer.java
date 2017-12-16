package cresla.entities.containers;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Module;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ModuleContainer implements Container {

    private int moduleCapacity;
    private LinkedList<Module> modulesByInput;
    private Map<Integer, EnergyModule> energyModules;
    private Map<Integer, AbsorbingModule> absorbingModules;

    public ModuleContainer(int moduleCapacity) {
        this.moduleCapacity = moduleCapacity;
        this.modulesByInput = new LinkedList<>();
        this.energyModules = new LinkedHashMap<Integer, EnergyModule>();
        this.absorbingModules = new LinkedHashMap<Integer, AbsorbingModule>();
    }

    public void addEnergyModule(EnergyModule energyModule) {
        if (energyModule == null) {
            throw new IllegalArgumentException();
        }

        if (this.modulesByInput.size() == this.moduleCapacity) {
            this.removeOldestModule();
        }

        this.energyModules.put(energyModule.getId(), energyModule);
        this.modulesByInput.addLast(energyModule);
    }

    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        if (absorbingModule == null) {
            throw new IllegalArgumentException();
        }

        if (this.modulesByInput.size() == this.moduleCapacity) {
            this.removeOldestModule();
        }

        this.absorbingModules.put(absorbingModule.getId(), absorbingModule);
        this.modulesByInput.addLast(absorbingModule);
    }

    @Override
    public long getTotalEnergyOutput() {
        return this.energyModules.values().stream()
                .mapToLong(EnergyModule::getEnergyOutput)
                .sum();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.absorbingModules.values().stream()
                .mapToLong(AbsorbingModule::getHeatAbsorbing)
                .sum();
    }

    private void removeOldestModule() {
        int removeId = this.modulesByInput.removeFirst().getId();

        if (this.energyModules.containsKey(removeId)) {
            this.energyModules.remove(removeId);
        }

        if (this.absorbingModules.containsKey(removeId)) {
            this.absorbingModules.remove(removeId);
        }
    }
}