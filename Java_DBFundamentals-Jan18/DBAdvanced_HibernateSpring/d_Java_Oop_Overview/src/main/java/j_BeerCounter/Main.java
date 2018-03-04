package j_BeerCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        try {
            while (!"End".equals(input)) {
                String[] data = input.split("\\s+");
                BeerCounter.BuyBeer(Integer.parseInt(data[0]));
                BeerCounter.DrinkBeer(Integer.parseInt(data[1]));
                input = reader.readLine();
            }
        } catch (Exception e) {
            // judge input does not include "End"
        } finally {
            System.out.printf("%d %d", BeerCounter.beersInStock, BeerCounter.beersDrankCount);
        }
    }
}
