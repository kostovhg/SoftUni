package d_Froggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Lake> lakes = new ArrayList<>();

        String input = null;
        while (!"END".equalsIgnoreCase(input = reader.readLine())) {
            lakes.add(new Lake(input.split("(, )|[, ]")));
        }

        for (Lake lake : lakes) {
            List<String> frogJumps = new ArrayList<>();
            for (Integer element : lake) {
                frogJumps.add(element.toString());
            }

            System.out.println(String.join(", ", frogJumps));
        }
    }
}

