package f_KingsGambitExtended;

import f_KingsGambitExtended.contracts.Observer;
import f_KingsGambitExtended.contracts.Subject;
import f_KingsGambitExtended.events.KillEvent;
import f_KingsGambitExtended.models.Footman;
import f_KingsGambitExtended.models.King;
import f_KingsGambitExtended.models.RoyalGuard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        Subject king = new King(reader.readLine());

        String[] royalGuardsNames = readStringArray(reader.readLine());
        String[] footmenNames = readStringArray(reader.readLine());

        Map<String, Observer> killableUnits = new LinkedHashMap<>();


        for (String royalGuardName : royalGuardsNames) {
            Observer royalGuard = new RoyalGuard(royalGuardName);
            killableUnits.put(royalGuardName, royalGuard);
            king.addObserver(royalGuard);
        }

        for (String footmanName : footmenNames) {
            Observer footman = new Footman(footmanName);
            killableUnits.put(footmanName, footman);
            king.addObserver(footman);
        }

        while (true) {
            String[] tokens = readStringArray(reader.readLine());

            if (tokens[0].equalsIgnoreCase("End")) {
                break;
            }

            switch (tokens[0]) {
                case "Attack":
                    king.notifyObservers();
                    break;
                case "Kill":
                    Observer observer =  killableUnits.get(tokens[1]);
                    KillEvent event = new KillEvent();
                    event.executeEvent(observer, king);
                    break;
            }
        }

    }

    private static String[] readStringArray(String input) {
        return input.split("\\s+");
    }
}
