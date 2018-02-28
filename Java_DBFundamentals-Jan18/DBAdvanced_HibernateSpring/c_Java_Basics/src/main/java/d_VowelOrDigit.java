import java.util.Scanner;

public class d_VowelOrDigit {

    public static final String vowels = "aeiou";

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);

        String inputString = scann.nextLine();
        try {
            int digit = Integer.parseInt(inputString);
            System.out.println("digit");
        } catch (NumberFormatException nfe){
            if(vowels.contains((inputString.toLowerCase()))){
                System.out.println("vowel");
            } else {
                System.out.println("other");
            }
        }

    }
}
