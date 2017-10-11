import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p06_ReverseAndExclude {
    private static Scanner sc = new Scanner(System.in);
    private static List<Integer> list =  Arrays
            .stream(sc.nextLine().split("\\s+"))
            .map(Integer::valueOf)
            .collect(Collectors.toCollection(ArrayList::new));

    public static void main(String[] args) {
        Collections.reverse(list);
        int n = Integer.valueOf(sc.nextLine());
        Predicate<Integer> checkDiv = i -> i % n == 0;
        list.removeIf(checkDiv);
        Consumer<Integer> print = p ->
                System.out.printf("%d ", p);
        list.forEach(print);

    }
}