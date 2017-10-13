import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class p05_MinEvenNumber {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        OptionalDouble min = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(n -> !n.isEmpty())
                .mapToDouble(Double::valueOf)
                .filter(n -> n % 2 == 0)
                .min();

        if(min.isPresent()) System.out.printf("%.2f%n", min.getAsDouble());
        else System.out.println("No match");
    }
}