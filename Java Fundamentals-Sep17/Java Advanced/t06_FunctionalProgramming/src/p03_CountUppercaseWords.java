import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p03_CountUppercaseWords {
    private static Scanner scann = new Scanner(System.in);

    public static void main(String[] args) {

        List<String> text = Arrays.stream(scann.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        Predicate<String> checkUpperCase = s ->
                s.charAt(0) == s.toUpperCase().charAt(0);
        ArrayList<String> result = new ArrayList<>();

        for (String str :
                text) {
            if(checkUpperCase.test(str)) {
                result.add(str);
            }
        }
        System.out.println(result.size());
        result.forEach(System.out::println);
    }
}