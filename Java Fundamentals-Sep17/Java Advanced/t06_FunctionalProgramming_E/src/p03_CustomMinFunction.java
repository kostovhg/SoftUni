import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class p03_CustomMinFunction {
    private static Supplier<Integer[]> read = () -> Arrays
            .stream((new Scanner(System.in)).nextLine().split("\\s+"))
                    .map(Integer::valueOf).toArray(Integer[]::new);
    private static Function<Integer[], Integer> smallest = x -> Stream.of(x)
            .min(Integer::compareTo).orElse(Integer.MIN_VALUE);

    public static void main(String[] args) {
        Integer[] input = read.get();
        System.out.println(smallest.apply(input));


    }
}