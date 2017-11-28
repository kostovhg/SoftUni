package j_InfernoInfinity;

import j_InfernoInfinity.contracts.api.Weapon;
import j_InfernoInfinity.contracts.impl.WeaponImpl;
import j_InfernoInfinity.enumerations.GemType;
import j_InfernoInfinity.enumerations.WeaponType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Weapon> weapons = new LinkedHashMap<>();

        String input;
        while (!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(";");
            try {
                switch (tokens[0]) {
                    case "Create":
                        weapons.putIfAbsent(tokens[2], new WeaponImpl(WeaponType.valueOf(tokens[1]), tokens[2]));
                        break;
                    case "Add":
                        weapons.get(tokens[1]).addGem(GemType.valueOf(tokens[3]), Integer.parseInt(tokens[2]));
                        break;
                    case "Remove":
                        weapons.get(tokens[1]).removeGem(Integer.parseInt(tokens[2]));
                        break;
                    case "Print":
                        System.out.println(weapons.get(tokens[1]).toString());
                        break;
                }
            } catch (Exception e) {
                // todo:
            }
        }

       /*
       for (Map.Entry<String, Weapon> weapon : weapons.entrySet()) {
            System.out.println(weapon);
        }
        */
    }
}