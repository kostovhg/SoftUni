import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by uKBo on 7/17/2017.
 */
public class p02_Largest3Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numbers = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(numbers.stream().sorted((a, b) -> {
            return b - a;})
            .limit(3)
                .map(Object::toString)
                .collect(Collectors.joining(" ")));
    }
}
