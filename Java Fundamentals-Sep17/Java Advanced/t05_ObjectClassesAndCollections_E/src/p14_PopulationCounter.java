import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class p14_PopulationCounter {
    private static LinkedHashMap<String, LinkedHashMap<String, Long>> cities = new LinkedHashMap<>();
    private static LinkedHashMap<String, Long> countries = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        String[] input;
        while(!(input = scann.nextLine().split("\\|"))[0]
                .equalsIgnoreCase("report")){
            String country = input[1];
            String city = input[0];
            Long pop = Long.parseLong(input[2]);
            if(!cities.containsKey(country)){
                cities.put(country, new LinkedHashMap<>());
            }
            cities.get(country).put(city, pop);
            if(!countries.containsKey(country)){
                countries.put(country, 0L);
            }
            countries.put(country, countries.get(country) + pop);
        }

        // sorting is taken from https://pastebin.com/Q3ZxGvuZ
        // with author https://softuni.bg/users/profile/show/kaloyannikov
        cities.entrySet().stream() // stream the collection of key-value pairs in full collection
                .sorted((c1, c2) -> countries.get(c2.getKey()) // sort countries based on additional collection
                        .compareTo(countries.get(c1.getKey()))) // containing only countries populations
                // after countries are sorted based on their population
                // loop trough them with anonymous function
                // where 'country' is key from both collections
                .forEach(country -> {
                    System.out.format("%s (total population: %d)%n",
                            country.getKey(), countries.get(country.getKey()));
                    // reach each country nested LinkedHashMap, aka its value
                    country.getValue().entrySet() // from those values (cities) sort based on their values (population)
                            .stream().sorted((t1, t2) -> t2.getValue().compareTo(t1.getValue()))
                            // and from sorted cities, take the pair {city, population}
                            .forEach(city -> System.out.format("=>%s: %d%n", city.getKey(), city.getValue()));
                });

    }
}