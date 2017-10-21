import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02_LetterExpression {
    private static Long total = 0L;
    private static Matcher matcher = Pattern.compile("(\\d+)([^0-9]*)")
            .matcher((new Scanner(System.in)).nextLine());
    private static boolean even = true;

    public static void main(String[] args) {
        while (matcher.find()){
            Long num = Long.valueOf(matcher.group(1));
            if(even) total += num;
            else  total -= num;
            even = (matcher.group(2).length() & 1) == 0;
        }
        System.out.println(total);
    }
}
