package p03_GenericSwapMethodStings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Box> objects = new ArrayList<>();

        int count = Integer.parseInt(reader.readLine());

        for (int i = 0; i < count; i++) {
            objects.add(new Box(reader.readLine()));
        }

        int[] swapIndexes = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Box.swap(objects, swapIndexes[0], swapIndexes[1]);

        for (Box box : objects) {
            System.out.println(box);
        }
    }

}
