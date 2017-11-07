package app_Avatar;

import app_Avatar.Entities.Nation;

import java.util.List;
import java.util.Map;

import static app_Avatar.Constants.*;

class Controller {
    static void interpretCommand(List<String> tokens) {
        String command = tokens.get(0);
        Element nation = getElement(tokens.get(1));
        switch (command.toLowerCase()){
            case "status":
                returnStatus(nation, tokens.get(1));
                break;
            case "war":
                WARS_BY_NATIONS.add(tokens.get(1));
                implementWar();
                break;
        }
    }

    private static void implementWar() {
        Element strongestNation = getStrongestNation();
        for (Element n : NATIONS.keySet()) {
            if(n != strongestNation){
                NATIONS.get(n).getBenders().clear();
                NATIONS.get(n).getMonuments().clear();
            }
        }
    }

    private static Element getStrongestNation() {
        double power = Long.MIN_VALUE;
        Element element = null;
        for (Map.Entry<Element, Nation> n : NATIONS.entrySet()) {
            double currentPower = n.getValue().getNationPower();
            if(currentPower > power) {
                power = currentPower;
                element = n.getKey();
            }
        }
        return element;
    }

    private static void returnStatus(Element element, String nation) {
        System.out.println(nation + " Nation");
        System.out.print(NATIONS.get(element));
    }
}
