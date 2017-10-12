import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p11_PredicateParty {
    private static Scanner sc = new Scanner(System.in);
    private static LinkedHashMap<String, Integer> guests = Arrays
            .stream(sc.nextLine().split("\\s+"))
            .collect(Collectors.toMap(e -> e, e -> 1, Integer::compareTo, LinkedHashMap::new));

    public static void main(String[] args) {

        Consumer<LinkedHashMap<String, Integer>> print = createPrint();
        String line;
        while(!(line = sc.nextLine()).equals("Party!")){
            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            String condition = tokens[1];
            String parameter = tokens[2];
            Predicate<String> tester = createTester(condition, parameter);
            guests.entrySet().stream()
                    .filter(e -> tester.test(e.getKey()))
                    .forEach(e -> guests.put(e.getKey(),
                            (command.equalsIgnoreCase("remove")
                                    ? 0
                                    : e.getValue() * 2)));
        }

        if(guests.values().stream().anyMatch(x -> x != 0)){
            print.accept(guests);

        } else {
            System.out.println("Nobody is going to the party!");
        }

    }

    private static Consumer<LinkedHashMap<String, Integer>> createPrint() {
        return (e) -> {
            List<String> temp = new LinkedList<>();
            for (Map.Entry<String, Integer> entry:
            guests.entrySet()){
                for (int i = 0; i < entry.getValue(); i++) {
                    temp.add(entry.getKey());
                }
            }
            System.out.printf("%s", String.join(", ", temp));
            System.out.println(" are going to the party!");
        };
    }

    private static Predicate<String> createTester(String con, String par) {
        switch(con.toLowerCase()){
            case "startswith":
                return x -> x.substring(0, par.length()).equals(par);
            case "endswith":
                int len = par.length();
                return x -> x.substring(x.length() - len, x.length()).equals(par);
            case "length":
                return x -> x.length() == Integer.parseInt(par);
        }
        return x -> x.length() > 0;
    }
}