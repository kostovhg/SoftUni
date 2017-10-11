import java.util.Scanner;
import java.util.function.Predicate;

public class p04_FindEvensOrOdds {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        String[] tokens = scanner.nextLine().split("\\s+");
        Integer start = Integer.valueOf(tokens[0]);
        Integer end = Integer.valueOf(tokens[1]);
        String condition = scanner.nextLine();

        Predicate<Integer> tester = createTest(condition);
        for (int i = start; i <= end; i++) {
            if(tester.test(i)) {
                System.out.printf("%d ", i);
            }
        }

    }

    private static Predicate<Integer> createTest(String condition) {
        switch (condition){
            case "odd":
                return x -> x % 2 != 0;
            case "even":
                return x -> x % 2 == 0;
        }
        return x -> x % 3 == 1;
    }
}