import java.util.Scanner;
import java.util.function.Predicate;

public class p07_PredicateForNames {
    private static Scanner sc = new Scanner(System.in);
    private static String[] names;

    public static void main(String[] args) {
        int len = Integer.valueOf(sc.nextLine());
        names = sc.nextLine().split("\\s+");

        Predicate<String> checkLen = x -> x.length() <= len;

        for (String name :
                names) {
            if (checkLen.test(name)) System.out.println(name);
        }

    }
}