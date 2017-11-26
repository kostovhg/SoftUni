package e_CardCompareTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CardRank rank = CardRank.valueOf(reader.readLine().toUpperCase());
        CardSuit suit = CardSuit.valueOf(reader.readLine().toUpperCase());
        Card cardOne = new Card(rank, suit);
        rank = CardRank.valueOf(reader.readLine().toUpperCase());
        suit = CardSuit.valueOf(reader.readLine().toUpperCase());
        Card cardTwo = new Card(rank, suit);

        Card bigger;

        if(cardOne.compareTo(cardTwo) > 0){
            bigger = cardOne;
        } else {
            bigger = cardTwo;
        }

        System.out.println(bigger) ;

    }
}