import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p07_ValidUsernames {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("^[\\w-]{3,16}$");
        String input;
        List<String> matches = new ArrayList<>();
        boolean hasMatches = false;
        while(!(input = sc.nextLine()).equals("END")){
            Matcher matcher = pattern.matcher(input);
            if(matcher.find()){
                matches.add("valid");
                hasMatches = true;
            } else {
                matches.add("invalid");
            }
        }
        if(hasMatches) {
            for (String str :
                    matches) {
                System.out.println(str);
            }
        }
    }
}