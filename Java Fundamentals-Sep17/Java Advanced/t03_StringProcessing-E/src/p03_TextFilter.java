import java.util.Arrays;
import java.util.Scanner;

public class p03_TextFilter {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] banned = scan.nextLine().split(", ");
        StringBuilder text = new StringBuilder(scan.nextLine());

        for (String ban :
                banned) {
            int len = ban.length();
            char[] chars = new char[len];
            Arrays.fill(chars, '*');
            String mask = new String(chars);
            int startIndex = text.indexOf(ban);
            while(startIndex != -1){
                text.replace(startIndex, startIndex + len, mask);
                startIndex = text.indexOf(ban, startIndex + 1);
            }
        }

        System.out.println(text);

    }
}