package h_BookLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Library library = new Library();
        int booksCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < booksCount; i++) {
            library.addBook(new Book(reader.readLine().split("\\s+")));
        }

        StringBuilder result = new StringBuilder();

        library.getAllPricesGroupedByAuthor()
                     .entrySet()
                     .stream()
                     .sorted((x1, x2) -> {
                         if (x2.getValue().compareTo(x1.getValue()) == 0) {
                             return x1.getKey().compareTo(x2.getKey());
                         } else {
                             return x2.getValue().compareTo(x1.getValue());
                         }})
                .forEach(x -> System.out.println(String.format("%s -> %.2f", x.getKey(), x.getValue())));

    }
}