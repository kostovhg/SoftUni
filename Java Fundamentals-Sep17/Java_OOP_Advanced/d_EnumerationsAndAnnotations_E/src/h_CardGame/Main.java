package h_CardGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Card> cards = new LinkedList<>();

        String firstPlayerName = reader.readLine();
        String secondPlayerName = reader.readLine();

        String input;
        int index = 0;
        for (int i = 0; i < 10;) {
            input = reader.readLine();
            try {
                String[] tokens = input.split("\\s+of\\s+");
                Rank rank = Rank.valueOf(tokens[0]);
                Suit suit = Suit.valueOf(tokens[1]);
                Card card = new Card(rank, suit);
                if(cards.stream().anyMatch(c -> c.compareTo(card) == 0)){
                    System.out.println("Card is not in the deck.");
                    continue;
                }
                if(cards.size() < 1) {
                    cards.add(card);
                    i++;
                    continue;
                }
                if(card.compareTo(cards.get(index)) > 0){
                    index = i;
                }
                cards.add(card);
                i++;
            } catch (Exception e){
                System.out.println("No such card exists.");
            }
        }

        System.out.printf("%s wins with %s.", (index < 5) ? firstPlayerName : secondPlayerName, cards.get(index));
    }
}