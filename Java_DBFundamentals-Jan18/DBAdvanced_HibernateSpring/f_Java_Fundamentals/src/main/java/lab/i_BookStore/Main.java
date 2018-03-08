package lab.i_BookStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Stream<Book> books = Stream.of(
                new Book("Vinetu3", 20d, "Karl Mai"),
                new Book("Vinetu1", 20d, "Karl Mai"),
                new Book("Vinetu2", 15d, "Karl Mai"),
                new Book("Sherlock Holmes", 12d, "Arthur C. Doyl"),
                new Book("The Lost World", 43d, "Arthur C. Doyl"));
        /* // Solution from lab presentation
        books.collect(
                Collectors.groupingBy(Book::getAuthor,
                        Collectors.summingDouble(Book::getPrice))
        ).entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.<String, Double>comparingByKey()))
                .forEach(System.out::println);
                */

        Map<String, Double> collectBooks =
                books.collect(
                        Collectors.groupingBy(Book::getAuthor,
                                Collectors.summingDouble(Book::getPrice)
                        ));

        collectBooks.entrySet().stream()
                .sorted(
                        Comparator.comparing(
                                Map.Entry<String, Double>::getValue).reversed()
                                .thenComparing(Map.Entry::getKey))
                .forEach(System.out::println);
                /*.forEach(kvp ->
                        System.out.printf(
                                "%s=%s%n",
                                kvp.getKey(),
                                kvp.getValue()));*/
    }


}