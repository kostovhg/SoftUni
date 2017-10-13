import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class p08_BoundedNumbers {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<Integer> bounds = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::valueOf).collect(Collectors.toList());

        List<Integer> numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::valueOf)
                .filter( x ->
                Collections.min(bounds) <= x
                && x <= Collections.max(bounds))
                .collect(Collectors.toList());

        System.out.println(String.join(" ",
                numbers.stream().map(Object::toString).toArray(String[]::new)));

    }
}