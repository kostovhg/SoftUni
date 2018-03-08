package lab.a_TakeTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> numbers = Arrays.asList(reader.readLine().split("\\s+"));
        numbers.stream()
                .mapToInt(Integer::parseInt)
                .filter(n -> 10 <= n && n <= 20)
                .distinct()
                .limit(2)
                .forEach(System.out::println);
    }
}