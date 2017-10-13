import jdk.nashorn.internal.runtime.JSType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static jdk.nashorn.internal.runtime.JSType.isNumber;

public class p06_FindAndSumIntegers {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Optional<Integer> sumOfIntsGT20 =
                Arrays.stream(reader.readLine().split("\\s+"))
                .filter(x -> x.matches("[-|+]?\\d+"))
                .map(Integer::valueOf)
                .reduce((x, y) -> x + y);
        if(sumOfIntsGT20.isPresent()) System.out.println(sumOfIntsGT20.get());
        else System.out.println("No match");

    }
}