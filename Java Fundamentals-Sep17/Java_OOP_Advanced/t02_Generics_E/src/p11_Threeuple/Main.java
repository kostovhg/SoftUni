package p11_Threeuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split("\\s+");
        Threeuple<String, String, String> firstResult = new Threeuple<>(line[0] + " " + line[1], line[2], line[3]);
        line = reader.readLine().split("\\s+");
        Threeuple<String, Integer, Boolean> secondResult = new Threeuple<>(line[0], Integer.parseInt(line[1]), ("drunk".equals(line[2])));
        line = reader.readLine().split("\\s+");
        Threeuple<String, Double, String> thirdResult = new Threeuple<>(line[0], Double.parseDouble(line[1]), line[2]);

        System.out.println(firstResult);
        System.out.println(secondResult);
        System.out.println(thirdResult);

    }
}
