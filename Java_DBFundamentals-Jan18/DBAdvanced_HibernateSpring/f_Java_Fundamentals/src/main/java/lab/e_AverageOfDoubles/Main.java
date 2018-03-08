package lab.e_AverageOfDoubles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> elements = Arrays.asList(reader.readLine().split("\\s+"));

        OptionalDouble average = elements.stream()
                .filter(n -> !n.isEmpty())
                .mapToDouble(Double::valueOf)
                .average();

        if (average.isPresent()) {
            System.out.printf("%.2f", average.getAsDouble());
            System.out.println();
        } else {
            System.out.println("No match");
        }

        // DoubleStream doubleStream = elements.stream().mapToDouble(Double::parseDouble);

        // System.out.println(doubleStream.average().getAsDouble());
    }
}