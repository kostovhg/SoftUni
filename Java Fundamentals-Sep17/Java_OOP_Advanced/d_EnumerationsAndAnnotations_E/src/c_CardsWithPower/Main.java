package c_CardsWithPower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        RankPower rank = RankPower.valueOf(reader.readLine().toUpperCase());
        SuitPower suit = SuitPower.valueOf(reader.readLine().toUpperCase());

        int power = rank.getPower() + suit.getPower();

        System.out.println("Card name: " + rank.name() + " of " + suit.name() + "; Card power: " + power);

    }
}