package b_KingsGambit;

import b_KingsGambit.contracts.Attackable;
import b_KingsGambit.contracts.KillableUnits;
import b_KingsGambit.models.Footman;
import b_KingsGambit.models.King;
import b_KingsGambit.models.RoyalGuard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Attackable theKing = new King(reader.readLine());
        Map<String, KillableUnits> theKingsUnits = new HashMap<>();
        String[] newUnits = reader.readLine().split("\\s+");
        for (String s : newUnits) {
            theKingsUnits.put(s, new RoyalGuard(s, theKing));
        }
        newUnits = reader.readLine().split("\\s+");
        for (String s : newUnits) {
            theKingsUnits.put(s, new Footman(s, theKing));
        }

        String[] command;
        while (true) {
            command = reader.readLine().split(("\\s+"));
            if(command[0].equalsIgnoreCase("End")) {
                break;
            }
            switch (command[0]) {
                case "Kill":
                    theKingsUnits.remove(command[1]);
                    theKing.kill(command[1]);
                    break;
                case "Attack":
                    theKing.respondToAttack();
                    break;
            }
        }
    }
}
