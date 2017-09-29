import java.util.Collections;
import java.util.Scanner;

public class p09_TeroristsWin {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /*
        TODO: try with StringBuilder/asCharArray and with only one loop
         */
        String input = sc.nextLine();
        int bombsCount = (input.length() - input.replace("|","").length()) / 2;
        int bombStart = -1;
        int bombEnd = -1;
        // array that will keep coordinates and power of each bomb in format:
        // [bomb1start, bomb1end, bomb1power, bomb2start, bomb2end, bomb2power..]
        int[] bombIndexes = new int[(bombsCount * 3)];
        for (int i = 0; i < bombIndexes.length - 2; i += 3) {
            bombStart = input.indexOf('|', bombEnd + 1);
            bombEnd = input.indexOf('|', bombStart + 1);
            bombIndexes[i] = bombStart;
            bombIndexes[i + 1] = bombEnd;
            bombIndexes[i + 2] = BombDistance(input.substring(bombStart + 1, bombEnd));
        }
        StringBuilder result = new StringBuilder(input);
        for (int i = 0; i < bombIndexes.length; i += 3) {
            int replaceFrom = Math.max(0, bombIndexes[i] - bombIndexes[i + 2]);
            int replaceTo = Math.min(bombIndexes[i + 1] + bombIndexes[i + 2], result.length() - 1);
            result.replace(replaceFrom, replaceTo + 1, String.join("",
                            Collections.nCopies(replaceTo - replaceFrom + 1 , ".")));
        }
        System.out.println(result);
    }

    private static int BombDistance(String substring) {
        int pow = 0;
        for (char ch:
             substring.toCharArray()) {
            pow += (int)ch;
        }
        return pow % 10;
    }
}