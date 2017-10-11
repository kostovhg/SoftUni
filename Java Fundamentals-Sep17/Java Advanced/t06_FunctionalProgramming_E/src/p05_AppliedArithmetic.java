import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class p05_AppliedArithmetic {
    private static Scanner sc = new Scanner(System.in);
    private static Supplier<ArrayList<Integer>> readIntArrayList = () -> Arrays
            .stream(sc.nextLine().split("\\s+"))
            .map(Integer::valueOf).collect(Collectors.toCollection(ArrayList::new));
    private static ArrayList<Integer> list = new ArrayList<>(readIntArrayList.get());

    public static void main(String[] args) {
        String input;

        while (!(input = sc.nextLine()).equalsIgnoreCase("end")) {
            if (input.equalsIgnoreCase("print")) {
                list.forEach(x -> System.out.printf("%d ", x));
                System.out.println();
            } else {
                UnaryOperator<Integer> change = createFunc(input);
                for (int i = 0; i < list.size(); i++) {
                    if (change != null) {
                        list.set(i, change.apply(list.get(i)));
                    }
                }
            }
        }
    }

    private static UnaryOperator<Integer> createFunc(String input) {
        switch (input){
            case "add":  return (i) -> i + 1;
            case "multiply": return i -> i * 2;
            case "subtract": return i -> i - 1;
        }
        return null;
    }


}
