import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by uKBo on 7/17/2017.
 */
public class p01_SumMInMaxAverage {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < n; i++){
            numbers.add(Integer.parseInt(scanner.nextLine()));
        }

        int sum = numbers.stream().mapToInt(Integer::valueOf).sum();
        int min = numbers.stream().min(Integer::compareTo).get();
        int max = numbers.stream().max(Integer::compareTo).get();
        double average = numbers
                .stream()
                .mapToDouble(Double::valueOf)
                .average()
                .getAsDouble();

        System.out.println("Sum = " + sum);
        System.out.println("Min = " + min);
        System.out.println("Max = " + max);
        System.out.println("Average = " + average);
    }
}
