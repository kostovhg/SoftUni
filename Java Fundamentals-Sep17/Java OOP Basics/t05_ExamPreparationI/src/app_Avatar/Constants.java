package app_Avatar;

import app_Avatar.Entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Constants {
    public enum Element {
        AIR, WATER, FIRE, EARTH
    }

    static final HashMap<Element, Nation> NATIONS = getNationsMap();
    private static HashMap<Element, Nation> getNationsMap(){
        HashMap<Element, Nation> map = new HashMap<>();
        map.put(Element.AIR, new Nation());
        map.put(Element.EARTH, new Nation());
        map.put(Element.FIRE, new Nation());
        map.put(Element.WATER, new Nation());
        return map;
    }
    static final List<String> WARS_BY_NATIONS = new ArrayList<>();

    static Element getElement(String str) {
        if (str.contains("Air")) {
            return Element.AIR;
        } else if (str.contains("Earth")) {
            return Element.EARTH;
        } else if (str.contains("Fire")) {
            return Element.FIRE;
        } else {
            return Element.WATER;
        }
    }
}
