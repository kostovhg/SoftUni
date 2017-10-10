import java.util.Scanner;
import java.util.TreeMap;

public class p10_CountSymbols {


    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        TreeMap<Character, Integer> counts = new TreeMap<>();

        String input = scann.nextLine();

        for (int i = 0; i < input.length(); i++) {
            Character ch = input.charAt(i);
            if(!counts.containsKey(ch)){
                counts.put(ch, 1);
            } else {
                counts.put(ch, counts.get(ch) + 1);
            }
        }
        for (Character ch :
                counts.keySet()) {
            System.out.printf("%s: %d time/s%n", ch, counts.get(ch));
        }

    }
}