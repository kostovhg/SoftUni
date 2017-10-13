import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p07_MapDistricts {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        HashMap<String, List<Integer>> cities = new HashMap<>();
        /*HashMap<String, List<Integer>> cityDistr = Arrays
                .stream()*/
         List<String> tokens = Arrays.asList(reader.readLine().split("\\s+"));

        for (String token :
                tokens) {
            String[] tokenArgs = token.split(":");
            String city = tokenArgs[0];
            int districtPopulation = Integer.valueOf(tokenArgs[1]);

            cities.putIfAbsent(city, new ArrayList<>());
            cities.get(city).add(districtPopulation);
        }

        int bound = Integer.valueOf(reader.readLine());

        cities.entrySet().stream()
                .filter(getFilterByPopulationPredicate(bound))
                .sorted(getSortByDescendingPopulationComparator())
                .forEach(getPrintMapEntryConsumer());
    }

    private static Comparator<Map.Entry<String, List<Integer>>> getSortByDescendingPopulationComparator(){
        return (kv1, kv2) ->
                Integer.compare(
                        kv2.getValue().stream().mapToInt(Integer::valueOf).sum(),
                        kv1.getValue().stream().mapToInt(Integer::valueOf).sum());
    }

    private static Consumer<Map.Entry<String, List<Integer>>> getPrintMapEntryConsumer() {
        return kv -> {
            System.out.print(kv.getKey() + ": ");
            kv.getValue().stream()
                    .sorted(Comparator.reverseOrder())
                    .limit(5)
                    .forEach(dp -> System.out.print(dp + " "));
            System.out.println();
        };
    }

    private static Predicate<Map.Entry<String, List<Integer>>> getFilterByPopulationPredicate(Integer b){
        return kv -> (kv.getValue().stream()
        .mapToInt(Integer::valueOf).sum()) >= b;
    }
}