package p07_FoodShortage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<String, Buyer> buyers = new HashMap<>();

        int countOfPersons = Integer.parseInt(scanner.nextLine());

        String[] tokens;
        for (int i = 0; i < countOfPersons; i++) {
            tokens = scanner.nextLine().split("\\s+");
            Buyer buyer;
            if(tokens.length == 3) {
                buyer = new Rebel(tokens);
            } else {
                buyer = new Citizen(tokens);
            }
            buyers.putIfAbsent(tokens[0], buyer);
        }

        String input;
        while(!"End".equalsIgnoreCase(input = scanner.nextLine())){
            if(buyers.containsKey(input)){
                buyers.get(input).buyFood();
            }
        }

        long totalBuyedFood = 0L;
        for (Buyer buyer :
                buyers.values()) {
            totalBuyedFood += buyer.getBuyedFood();
        }

        System.out.println(totalBuyedFood);
    }
}
