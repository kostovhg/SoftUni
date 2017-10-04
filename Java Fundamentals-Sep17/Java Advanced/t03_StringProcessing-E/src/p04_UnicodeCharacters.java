import java.util.Scanner;

public class p04_UnicodeCharacters {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        StringBuilder text = new StringBuilder(scan.nextLine());

        for (int i = 0; i < text.length(); i++) {
            System.out.printf("\\u%04x",text.codePointAt(i));
        }
    }
}