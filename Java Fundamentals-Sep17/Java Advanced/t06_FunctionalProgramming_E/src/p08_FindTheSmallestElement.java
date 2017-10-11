import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class p08_FindTheSmallestElement {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Function<String, List<Integer>> readIntArr = x -> Arrays.
                stream(x.split("\\s+"))
                    .map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> arrList = readIntArr.apply(sc.nextLine());

        Function<List<Integer>, Integer> findSmallest = x -> {
            int min = 0;
            for (int i = 0; i < x.size() - 1; i++) {
                if(x.get(i) >= x.get(i + 1)) min = i + 1;
            }
            return min;
        };

        System.out.println(findSmallest.apply(arrList));
    }
}