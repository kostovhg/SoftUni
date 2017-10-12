import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class p09_CustomComparator {
    private static Scanner sc = new Scanner(System.in);
    private static Function<String, Integer[]> readIntArr = x -> Arrays
            .stream(x.split("\\s+"))
            .map(Integer::valueOf).toArray(Integer[]::new);

    public static void main(String[] args) {
        Integer[] intArr = readIntArr.apply(sc.nextLine());

        BiFunction<Integer, Integer, Integer> evenFirstOddLast = (a, b) ->{
            if(a % 2 == 0 && b % 2 != 0) return -1;
            if(a % 2 !=0 && b % 2 == 0) return 1;
            return a.compareTo(b);
        };

        Arrays.sort(intArr, evenFirstOddLast::apply);
        Arrays.stream(intArr).forEach(x -> System.out.print(x + " "));

    }
}