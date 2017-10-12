import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class p10_ListOfPredicates {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Integer num = Integer.parseInt(sc.nextLine());
        Integer[] divisors = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::valueOf).toArray(Integer[]::new);
        Predicate<Integer> divisible = d -> {
            for (Integer div :
                    divisors) {
                if(d % div != 0) return false;
            }
            return true;
        };

        for (int i = 1; i <= num; i++) {
            if(divisible.test(i) ) System.out.printf("%d ", i);
        }
    }
}