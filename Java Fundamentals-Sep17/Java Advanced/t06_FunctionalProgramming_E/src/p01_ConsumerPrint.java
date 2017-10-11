import java.util.*;
import java.util.function.Consumer;

public class p01_ConsumerPrint {
    private static Scanner scann = new Scanner(System.in);
    private static Consumer<String> print = System.out::println;
    private static List<String> list = new ArrayList<>();
    private static Consumer<String> fill = x -> list.addAll(Arrays.asList(x.split("\\s+")));

    public static void main(String[] args) {
        fill.accept(scann.nextLine());

        for (String str :
                list) {
            print.accept(str);
        }

    }
}