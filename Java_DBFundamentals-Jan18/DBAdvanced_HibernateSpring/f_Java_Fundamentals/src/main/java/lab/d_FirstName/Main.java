package lab.d_FirstName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> names = Arrays.asList(reader.readLine().split("\\s+"));

        Character ch = reader.readLine().toLowerCase().charAt(0);

        Optional<String> first = names.stream().filter(name -> ch == name.toLowerCase().charAt(0))
                .sorted().findFirst();

        if (first.isPresent()) {
            System.out.println(first.get());
        } else {
            System.out.println("No matches");
        }

    }
}