import java.util.Scanner;
import java.util.regex.Pattern;

public class p11_ReplaceATag {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String line = scann.nextLine();
        StringBuilder input = new StringBuilder();
        while(!line.equals("END")){
            input.append(line + System.getProperty("line.separator"));
            line = scann.nextLine();
        }

        Pattern pattern = Pattern.compile("(<a)\\r?\\n?(.*?)(>)(\\r?\\n?.*?)\\r?\\n?(</a>)", Pattern.MULTILINE);
        String subst = "[URL$2]$4[/URL]";
        //pattern.matcher(input).replaceAll("(\\\\r)?\\\\n", System.getProperty("line.separator"));
        System.out.println(pattern.matcher(input).replaceAll(subst));


    }
}