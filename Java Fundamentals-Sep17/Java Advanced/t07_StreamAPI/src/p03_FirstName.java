import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class p03_FirstName {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> names = Arrays.asList(reader.readLine().split("\\s+"));

        HashSet<Character> letters = Arrays.stream(reader.readLine().split("\\s+"))
                .map(s -> s.toLowerCase().charAt(0))
                .collect(Collectors.toCollection(HashSet::new));

        Optional<String> first = names.stream()
                .filter(s ->
                    letters.contains(s.toLowerCase().charAt(0)))
                .sorted()
                .findFirst();

        System.out.printf("%s%n", (first.orElse("No match")));
    }
}