import java.util.Scanner;

public class p08_MelrahShake {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        String input = scann.nextLine();

        StringBuilder pattern = new StringBuilder(scann.nextLine());

        int firstIndex = input.indexOf(pattern.toString());
        int lastIndex = input.lastIndexOf(pattern.toString());
        StringBuilder output = new StringBuilder(input);

        while(lastIndex != -1 && lastIndex != firstIndex && firstIndex != -1 && pattern.length() > 0){
            output.replace(lastIndex, lastIndex + pattern.length(),"");
            output.replace(firstIndex, firstIndex + pattern.length(), "");

            pattern.deleteCharAt(pattern.length() / 2);
            firstIndex = output.indexOf(pattern.toString());
            lastIndex = output.lastIndexOf(pattern.toString());
            System.out.println("Shaked it.");
        }
        System.out.println("No shake.");
        System.out.println(output);

    }
}