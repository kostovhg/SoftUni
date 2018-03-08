package lab.e_DemoPrimitiveStreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] ints = { 1, 2, 3, 4 };

        IntStream intStream = IntStream.of(ints);

        List<Integer> list = new ArrayList<>();

        IntStream mappedIntStream = list.stream().mapToInt(n -> Integer.valueOf(n));

    }
}