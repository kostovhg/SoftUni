import java.util.Scanner;
import java.util.regex.Pattern;

public class p09_MatchFullName {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String regex = "^[A-Z][a-z]+ [A-Z][a-z]+$";
        String input = scann.nextLine();

        while(!input.equals("end")){
            if(Pattern.matches(regex, input))
                System.out.println(input);
            input = scann.nextLine();
        }

    }
}