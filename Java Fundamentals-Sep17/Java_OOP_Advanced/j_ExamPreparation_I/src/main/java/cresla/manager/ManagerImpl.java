package cresla.manager;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.*;
import cresla.models.modules.CooldownSystem;
import cresla.models.modules.CryogenRod;
import cresla.models.modules.HeatProcessor;
import cresla.models.reactors.CryoReactor;
import cresla.models.reactors.HeatReactor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {

    private int id;
    private Map<Integer, Reactor> reactorMap;
    private Map<Integer, Module> moduleMap;
    private int energyModulesCounter;
    private int absorbingModulesCounter;
    private int cryoReactorCounter;
    private int heatReactorCounter;

    public ManagerImpl() {
        this.reactorMap = new HashMap<>();
        this.moduleMap = new HashMap<>();
        this.energyModulesCounter = 0;
        this.absorbingModulesCounter = 0;
        this.cryoReactorCounter = 0;
        this.heatReactorCounter = 0;
        this.id = 1;
    }


    @Override
    public String reactorCommand(List<String> arguments) {
        Container container = new ModuleContainer(Integer.valueOf(arguments.get(3)));
        Reactor reactor = null;
        switch (arguments.get(1)) {
            case "Cryo":
                reactor = new CryoReactor(container, id, Integer.parseInt(arguments.get(2)));
                this.cryoReactorCounter++;
                break;
            case "Heat":
                reactor = new HeatReactor(container, id, Integer.parseInt(arguments.get(2)));
                this.heatReactorCounter++;
                break;
        }
        this.reactorMap.put(id, reactor);
        return String.format("Created %s Reactor - %d", arguments.get(1), id++);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        int reactorId = Integer.valueOf(arguments.get(1));
        Module module = null;
        switch (arguments.get(2)) {
            case "CryogenRod":
                EnergyModule cryogenRod = new CryogenRod(id, Integer.parseInt(arguments.get(3)));
                module = cryogenRod;
                this.reactorMap.get(reactorId).addEnergyModule(cryogenRod);
                this.energyModulesCounter++;
                break;
            case "HeatProcessor":
                AbsorbingModule heatProcessor = new HeatProcessor(id, Integer.parseInt(arguments.get(3)));
                module = heatProcessor;
                this.reactorMap.get(reactorId).addAbsorbingModule(heatProcessor);
                this.absorbingModulesCounter++;
                break;
            case "CooldownSystem":
                AbsorbingModule coolingSystem = new CooldownSystem(id, Integer.parseInt(arguments.get(3)));
                module = coolingSystem;
                this.reactorMap.get(reactorId).addAbsorbingModule(coolingSystem);
                this.absorbingModulesCounter++;
                break;
        }
        this.moduleMap.put(id, module);
        return String.format("Added %s - %d to Reactor - %d", arguments.get(2), id++, reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int target = Integer.parseInt(arguments.get(1));
        if (this.reactorMap.containsKey(target)) {
            return this.reactorMap.get(target).toString();
        } else {
            return this.moduleMap.get(target).toString();
        }
    }

    @Override
    public String exitCommand(List<String> arguments) {
        long totalEnergy = this.reactorMap.entrySet().stream()
                .mapToLong(r -> r.getValue().getTotalEnergyOutput()).sum();
        long totalHeat = this.reactorMap.entrySet().stream()
                .mapToLong(r -> r.getValue().getTotalHeatAbsorbing()).sum();

        return "Cryo Reactors: " + this.cryoReactorCounter + System.lineSeparator() +
                "Heat Reactors: " + this.heatReactorCounter + System.lineSeparator() +
                "Energy Modules: " + this.energyModulesCounter + System.lineSeparator() +
                "Absorbing Modules: " + this.absorbingModulesCounter + System.lineSeparator() +
                "Total Energy Output: " + totalEnergy + System.lineSeparator() +
                "Total Heat Absorbing: " + totalHeat;
    }
}
