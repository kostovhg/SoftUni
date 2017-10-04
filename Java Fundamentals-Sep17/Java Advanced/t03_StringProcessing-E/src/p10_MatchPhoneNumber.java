import java.util.Scanner;
import java.util.regex.Pattern;

public class p10_MatchPhoneNumber {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String regex = "^\\+359( |\\-)2\\1\\d{3}\\1\\d{4}$";
        String input = scann.nextLine();

        while(!input.equals("end")){
            if(Pattern.matches(regex, input))
                System.out.println(input);
            input = scann.nextLine();
        }

    }
}