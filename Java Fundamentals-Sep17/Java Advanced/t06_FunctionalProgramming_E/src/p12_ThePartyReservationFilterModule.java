import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p12_ThePartyReservationFilterModule {
    private static Scanner sc = new Scanner(System.in);
    private static HashSet<Predicate<String>> filters = new HashSet<>();
    private static HashSet<String[]> strFilters = new HashSet<>();

    public static void main(String[] args) {
        List<String> reservations = Arrays
                .stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));
        String line;
        while(!(line = sc.nextLine()).equals("Print")){
            String[] tokens = line.split(";");
            String command = tokens[0];
            String condition = tokens[1];
            String parameter = tokens[2];
                        switch(command.toLowerCase()){
                case "add filter":
                    strFilters.add(new String[]{condition, parameter});
                    break;
                case "remove filter":
                    strFilters
                            .removeIf(x -> x[0].equals(condition) 
                                    && x[1].equals(parameter));
                    break;
            }
        }

        strFilters.forEach(str -> filters.add(createTest(str[0], str[1])));

        System.out.println(String.join(" ",
                reservations.stream().filter(
                filters.stream().reduce(Predicate::and).orElse(t -> true))
                .collect(Collectors.toList())
        ));
    }

    private static Predicate<String> createTest(String con, String param) {
        switch (con.toLowerCase()){
            case "starts with": return x -> !x.startsWith(param);
            case "ends with": return x -> !x.endsWith(param);
            case "length": return x -> x.length() != Integer.parseInt(param);
            case "contains": return x -> !x.contains(param);
        }
        return String::isEmpty;
    }
}