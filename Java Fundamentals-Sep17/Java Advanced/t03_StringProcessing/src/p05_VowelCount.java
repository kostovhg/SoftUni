import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p05_VowelCount {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String input = sc.nextLine();
        Pattern pattern = Pattern.compile("[AEIOUYaeiouy]");
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        System.out.println("Vowels: " + count);
    }
}