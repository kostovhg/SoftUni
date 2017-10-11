import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class p02_SumNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");

        System.out.printf("Count = %d%n", input.length);
        biFunctionSum(input);
    }

    private static void biFunctionSum(String[] input){
        BiFunction<String, String, Integer> parser = (x, y) ->
                Integer.parseInt(x) + Integer.parseInt(y);
        int sum = 0;
        for (int i = 0; i <= input.length - 1; i += 2) {
            sum += parser.apply(input[i], (i == input.length -1) ? "0" : input[i + 1]);
        }
        System.out.println("Sum = " + sum);
    }

    private static void functionSum(String[] input){
        Function<String, Integer> parse = Integer::parseInt;
        int sum = 0;
        for (String s :
                input) {
            sum += parse.apply(s);
        }
        System.out.printf("Sum = %d%n", sum);
    }
}