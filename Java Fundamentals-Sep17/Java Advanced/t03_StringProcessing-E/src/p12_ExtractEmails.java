import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p12_ExtractEmails {
    private static Scanner sc = new Scanner(System.in);
    // https://regex101.com/r/MTm9Si/2
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String regex = "(?<=^| )(?<u>(?:[a-z0-9][\\w-\\.]*)+[a-z0-9]+)@(?<h>([a-z0-9]+\\-?[a-z0-9]+(\\.[a-z0-9]+)+))(?=[\\.,\\s]|$)";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(scann.nextLine());
        List<String> matches = new ArrayList<>();
        while(matcher.find()){
            matches.add(matcher.group());
        }
        for (String match :
                matches) {
            System.out.println(match);
        }

    }
}