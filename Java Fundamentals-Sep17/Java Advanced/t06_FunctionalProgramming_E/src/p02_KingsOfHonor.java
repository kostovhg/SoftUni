import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class p02_KingsOfHonor {
    private static Consumer<String> print = x -> System.out.printf("Sir %s%n", x);
    private static List<String> list = new ArrayList<>();
    private static Consumer<String> fill = x -> list.addAll(Arrays.asList(x.split("\\s+")));

    public static void main(String[] args) {
        fill.accept(new Scanner(System.in).nextLine());

        for (String str :
                list) {
            print.accept(str);
        }

    }
}