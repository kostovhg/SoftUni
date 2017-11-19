package p10_Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split("\\s+");
        Tuple<String, String> firstResult = new Tuple<>(line[0] + " " + line[1], line[2]);
        line = reader.readLine().split("\\s+");
        Tuple<String, Integer> secondResult = new Tuple<>(line[0], Integer.parseInt(line[1]));
        line = reader.readLine().split("\\s+");
        Tuple<String, Double> thirdResult = new Tuple<>(line[0], Double.parseDouble(line[1]));

        System.out.println(firstResult);
        System.out.println(secondResult);
        System.out.println(thirdResult);

    }
}
