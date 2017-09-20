package t01_IntroToJava;

import java.math.BigDecimal;
import java.util.Scanner;

public class p04_EuroTrip {
    public static void main(String[] args){
        // Create new scanner
        Scanner input = new Scanner(System.in);
        // Read the quantity as a double value
        double wurst = input.nextDouble();
        double pricePerKilo = 1.20;
        BigDecimal priceInBGN = new BigDecimal((pricePerKilo * wurst));
        // Multiply the price in BGN with price in DM
        BigDecimal exchangeRate = new BigDecimal("4210500000000");
        BigDecimal neededDM = exchangeRate.multiply(priceInBGN);

        System.out.printf("%.2f marks", neededDM);
    }
}
