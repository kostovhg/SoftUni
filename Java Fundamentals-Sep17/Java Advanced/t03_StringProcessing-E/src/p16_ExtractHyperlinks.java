import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p16_ExtractHyperlinks {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder html = new StringBuilder();
        String input = scan.nextLine();
        while(!input.equalsIgnoreCase("END")){
            html.append(input);
            input = scan.nextLine();
        }

        List<String> links = new ArrayList<>();
        Pattern linkPattern = Pattern
                .compile("(?<=<a\\s)\\s*.*?\\s*.*?\\s*(?=>)");
        String text = html.toString();
        Matcher matchLink = linkPattern.matcher(text);
        while (matchLink.find()){
            links.add(matchLink.group(0));
        }
        for (String link :
                links) {
            System.out.println(link);
        }

        Pattern hrefPattern = Pattern
                .compile("\"href\\\\s*=\\\\s*(['\\\"])?(?<addres>.*?)(?=(?(1)\\\\1|\\\\s))\"");
        System.out.println("test");
    }
}