package app_Avatar;

import app_Avatar.Entities.*;
import app_Avatar.Entities.Benders.*;
import app_Avatar.Entities.Monuments.AirMonument;
import app_Avatar.Entities.Monuments.EarthMonument;
import app_Avatar.Entities.Monuments.FireMonument;
import app_Avatar.Entities.Monuments.WaterMonument;

import java.util.Arrays;
import java.util.List;

import static app_Avatar.Constants.*;
import static app_Avatar.Controller.interpretCommand;

class Factory {

    static void readLine(String line) {
        List<String> tokens = Arrays.asList(line.split("\\s+"));
        Element elementType = getElement(tokens.get(1));
        if (tokens.get(0).equalsIgnoreCase("bender")) {
            NATIONS.get(elementType)
                    .addBender( createBender(elementType, tokens));
        } else if (tokens.get(0).equalsIgnoreCase("monument")) {
            NATIONS.get(elementType)
                    .addMonument(createMonument(elementType, tokens));
        } else {
            interpretCommand(tokens);
        }
    }

    private static Bender createBender(Element type, List<String> tokens){
        String name = tokens.get(2);
        int power = Integer.parseInt(tokens.get(3));
        double property = Double.parseDouble(tokens.get(4));
        switch (type){
            case AIR: return new AirBender(name, power, property);
            case EARTH: return new EarthBender(name, power, property);
            case FIRE: return new FireBender(name, power, property);
            case WATER:  return new WaterBender(name, power, property);
            default: return null;
        }
    }

    private static Monument createMonument(Element type, List<String> tokens) {
        String name = tokens.get(2);
        int affinity = Integer.parseInt(tokens.get(3));
        switch (type){
            case AIR: return new AirMonument(name, affinity);
            case EARTH: return new EarthMonument(name, affinity);
            case FIRE: return new FireMonument(name, affinity);
            case WATER: return new WaterMonument(name, affinity);
            default: return null;
        }
    }
}
