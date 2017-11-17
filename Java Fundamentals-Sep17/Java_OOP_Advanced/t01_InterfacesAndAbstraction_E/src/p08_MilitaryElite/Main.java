package p08_MilitaryElite;

import p08_MilitaryElite.entities.Mission;
import p08_MilitaryElite.entities.Repair;
import p08_MilitaryElite.entities.soldiers.*;
import p08_MilitaryElite.interfaces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static Map<Integer, ISoldier> soldiers;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        soldiers = new LinkedHashMap<>();

        String input;

        while (!"End".equals(input = reader.readLine())) {

            String[] tokens = input.split("\\s+");

            int id = Integer.parseInt(tokens[1]);
            String firstName = tokens[2];
            String lastName = tokens[3];

            switch (tokens[0]) {
                case "Private":
                    soldiers.putIfAbsent(id, new Private(id, firstName, lastName,
                            Double.valueOf(tokens[4])));
                    break;
                case "LeutenantGeneral":
                    soldiers.putIfAbsent(id, new LeutenantGeneral(id, firstName, lastName,
                                    Double.valueOf(tokens[4]),
                                    parsePrivates(soldiers, tokens)));
                    break;
                case "Engineer":
                    IEngineer engineer = new Engineer(
                            id, firstName, lastName,
                            Double.parseDouble(tokens[4]),
                            tokens[5],
                            parseRepairs(tokens));
                    if (engineer.getCorps() != null) {
                        soldiers.putIfAbsent(id, engineer);
                    }
                    break;
                case "Commando":
                    ICommando commando = new Commando(
                            id, firstName, lastName,
                            Double.parseDouble(tokens[4]),
                            tokens[5],
                            parseMissions(tokens));
                    if (commando.getCorps() != null) {
                        soldiers.putIfAbsent(id, commando);
                    }
                    break;
                case "Spy":
                    soldiers.putIfAbsent(id,
                            new Spy(
                                    id, firstName, lastName,
                                    tokens[4]));
                    break;
            }
        }

        for (ISoldier soldier :  soldiers.values()) {
            System.out.print(soldier);
        }
    }

    private static Collection<IRepair> parseRepairs(String... args) {
        Collection<IRepair> repairs = new ArrayList<>();
        for (int i = 6; i < args.length; i += 2) {
            repairs.add(new Repair(args[i], Integer.parseInt(args[i + 1])));
        }
        return repairs;
    }

    private static Collection<IMission> parseMissions(String... args) {
        Collection<IMission> missions = new ArrayList<>();
        for (int i = 6; i < args.length; i += 2) {
            IMission m = new Mission(args[i], args[i + 1]);
            if (m.getState() != null) {
                missions.add(m);
            }
        }
        return missions;
    }

    private static Collection<IPrivate> parsePrivates(Map<Integer, ISoldier> soldiers, String[] args){
        List<Integer> ids = Arrays.stream(args).skip(5).map(Integer::valueOf)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Collection<IPrivate> privates = new ArrayList<>();
        for (Integer id : ids) {
            privates.add(Private.class.cast(soldiers.get(id)));
        }
        return privates;
    }
}
