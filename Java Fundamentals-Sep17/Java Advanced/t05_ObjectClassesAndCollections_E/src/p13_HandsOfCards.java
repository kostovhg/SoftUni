import java.util.*;

public class p13_HandsOfCards {
    private static LinkedHashMap<String, HashSet<String>> players = new LinkedHashMap<>();
    private static String types = "0CDHS";
    private static String powers = "JQKA";

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        String input;
        while(!(input = scann.nextLine()).equalsIgnoreCase("JOKER")){
            String[] tokens = input.split(": ");
            String name = tokens[0];
            HashSet<String> cards = new HashSet<>(Arrays.
                    asList(tokens[1].split(",?\\s+")));
            if(!players.containsKey(name)){
                players.put(name, cards);
            } else {
                for (String card :
                        cards) {
                    players.get(name).add(card);
                }
            }
        }
        for (String name :
                players.keySet()) {
            System.out.printf("%s: %d%n", name, calcPoints(players.get(name)));
        }

    }

    private static int calcPoints(HashSet<String> cards) {
        int result = 0;
        for (String card :
                cards) {
            int type = types.indexOf(card.substring(card.length() - 1, card.length()));
            String p = card.substring(0, card.length() - 1);
            int power;
            if(!powers.contains(p)){
                power = Integer.parseInt(p);
            } else {
                power = powers.indexOf(p) + 11;
            }
            result += power * type;
        }
        return result;
    }
}