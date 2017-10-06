import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p09_PhoneNumbers {
    static String pairsRegex = "(?<name>[A-Z][a-zA-Z]*)(?:[^a-zA-Z0-9+]*)(?<num>\\+?\\d(?:(?:[\\-\\(\\)\\/  .])*\\d)+)+";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, String> numbers = new LinkedHashMap<>();
        StringBuilder text = new StringBuilder();
        String line = scan.nextLine();
        while(!line.equals("END")){
            text.append(line);
            line = scan.nextLine();
        }

        Matcher matcher = Pattern.compile(pairsRegex).matcher(text);
        while (matcher.find()){
            numbers.put(
                    matcher.group("name").toString(),
                    matcher.group("num").replaceAll("[^+\\d]",""));
        }
        if(numbers.size() == 0) {
            System.out.println("<p>No matches!</p>");
            return;
        }
        System.out.print("<ol>");
        for (String name :
                numbers.keySet()) {
            System.out.printf("<li><b>%s:</b> %s</li>", name, numbers.get(name));
        }
        System.out.print("</ol>");
    }
}