import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class p05_FilterByAge {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        int count = Integer.parseInt(scann.nextLine());
        LinkedHashMap<String, Integer> people = new LinkedHashMap<>();
        for (int i = 0; i < count; i++) {
            String[] tokens = scann.nextLine().split(", ");
            people.put(tokens[0], Integer.parseInt(tokens[1]));
        }
        String condition = scann.nextLine();
        Integer age = Integer.parseInt(scann.nextLine());
        String[] format = scann.nextLine().split("\\s+");

        // function to filter entries
        Predicate<Integer> tester = createTester(condition, age);

        // function for print format
        Consumer<Map.Entry<String, Integer>> printer = createPrinter(format);

        // print according conditions
        printFilteredStudents(people, tester, printer);
    }

    private static Consumer<Map.Entry<String,Integer>> createPrinter(String[] format) {
        // accept format, on its base decide
        // how to proceed with the given key value pair
        if(format.length > 1){
            switch (format[1]){
                case "age":
                    return person -> System.out.printf("%s - %d%n", person.getKey(), person.getValue());
                case "name":
                    return person -> System.out.printf("%d - %s%n", person.getValue(), person.getKey());
            }
        } else {
            switch(format[0]){
                case "age":
                    return person -> System.out.printf("%d%n", person.getValue());
                case "name":
                    return person -> System.out.printf("%s%n", person.getKey());
            }
        }
        return person -> System.out.print("no such person");
    }

    private static Predicate<Integer> createTester(String condition, Integer age) {
        // simple check the according given age
        switch(condition){
            case "younger": return x -> x < age;
            case "older": return x -> x >= age;
        }
        return null;
    }

    private static void printFilteredStudents(LinkedHashMap<String, Integer> people, Predicate<Integer> tester, Consumer<Map.Entry<String, Integer>> printer) {
        // iterate all entries in the map 'people'
        for (Map.Entry<String, Integer> person :
                people.entrySet()) {
            // if they meet the criteria 'tester'
            if (tester.test(people.get(person.getKey()))) {
                // send this pair to 'printer'
                printer.accept(person);
            }
        }
    }
}