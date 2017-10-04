import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p13_SentenceExtractor {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String match = scanner.nextLine();
        String text = scanner.nextLine();
        String regex = "[A-Z].[^.]+? " + match + "[\\s,].+?[\\.\\!\\?]";
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(text);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}