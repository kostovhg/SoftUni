package p08_MilitaryElite;

import p08_MilitaryElite.entities.Soldier;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<String, Soldier> soldiers = new LinkedHashMap<>();
        SoldierFactory factory = new SoldierFactory(soldiers);

        String input;

        while (!"End".equalsIgnoreCase(input = scanner.nextLine())) {
            Soldier soldier = factory.createSoldier(input);
            if(soldier != null){
                soldiers.put(soldier.getId(), soldier);
            }
        }

        for (Soldier soldier : soldiers.values()) {
            System.out.println(soldier);
        }
    }
}
