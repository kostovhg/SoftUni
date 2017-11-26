package d_CardsToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CardRank rank = CardRank.valueOf(reader.readLine().toUpperCase());
        CardSuit suit = CardSuit.valueOf(reader.readLine().toUpperCase());
        Card newCard = new Card(rank, suit);

        System.out.println(newCard);

    }
}