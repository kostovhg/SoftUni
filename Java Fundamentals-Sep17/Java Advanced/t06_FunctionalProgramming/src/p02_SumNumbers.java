import java.util.Scanner;
import java.util.function.Function;

public class p02_SumNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");

        System.out.printf("Count = %d%n", input.length);

        Function<String, Integer> parse = x -> Integer.parseInt(x);
        int sum = 0;
        for (String s :
                input) {
            sum += parse.apply(s);
        }
        System.out.printf("Sum = %d%n", sum);
    }
}