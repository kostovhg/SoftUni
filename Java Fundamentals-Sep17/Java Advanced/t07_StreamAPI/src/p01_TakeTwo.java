import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p01_TakeTwo {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::valueOf)
                .distinct()
                .filter(n -> 10 <= n && n <= 20)
                .limit(2)
                .forEach(n -> System.out.print(n + " "));

    }
}