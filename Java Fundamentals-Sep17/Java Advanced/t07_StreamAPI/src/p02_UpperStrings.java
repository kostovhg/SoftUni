import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p02_UpperStrings {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Arrays.stream(reader.readLine().split("\\s+"))
                .map(String::toUpperCase)
                .forEach(s -> System.out.print(s + " "));
    }
}