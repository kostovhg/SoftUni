package g_DecOfCards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputCommand = reader.readLine();
        if("Card Deck".equals(inputCommand)){
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    System.out.println(new Card(rank, suit));
                }
            }
        }
    }
}