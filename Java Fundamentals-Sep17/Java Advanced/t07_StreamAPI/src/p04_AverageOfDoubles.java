import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class p04_AverageOfDoubles {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        /*double[] strNums = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToDouble(Double::parseDouble).toArray();*/

        OptionalDouble average = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(n -> !n.isEmpty())
                .mapToDouble(Double::valueOf)
                .average();

        System.out.printf((average.isPresent() ?
                String.format("%.2f",average.getAsDouble()) :
                "No match"));

    }
}