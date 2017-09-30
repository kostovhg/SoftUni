import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p06_ExtractTags {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile(".*?(</?.+?>).*?");

        String input;
        while(!"END".equals(input = sc.nextLine())){
            Matcher matcher = pattern.matcher(input);
            while(matcher.find()){
                System.out.println(matcher.group(1));
            }
        }
    }
}