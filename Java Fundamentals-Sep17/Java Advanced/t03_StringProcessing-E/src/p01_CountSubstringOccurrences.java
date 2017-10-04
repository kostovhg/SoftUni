import java.util.Scanner;

public class p01_CountSubstringOccurrences {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        String sub = scanner.nextLine().toLowerCase();

        int index = input.indexOf(sub);
        int counter = 0;
        while (index != -1) {
            index = input.indexOf(sub, index + 1);
            counter++;
        }
        System.out.println(counter);
    }
}