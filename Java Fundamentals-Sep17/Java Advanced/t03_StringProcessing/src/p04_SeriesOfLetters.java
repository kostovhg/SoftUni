import java.util.Scanner;

public class p04_SeriesOfLetters {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String input = sc.nextLine();
        System.out.println(input.replaceAll("((.)\\2+)","$2"));
    }
}