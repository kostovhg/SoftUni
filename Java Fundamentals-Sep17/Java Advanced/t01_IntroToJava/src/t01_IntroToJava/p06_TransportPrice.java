package t01_IntroToJava;

import java.util.Scanner;

public class p06_TransportPrice {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Double distance = Double.parseDouble(input.nextLine());
        String dayPart = input.nextLine();
        Double price = 0.0;
        if(distance >= 100){
            price = distance * 0.06;
        } else if (distance >= 20) {
            price = distance * 0.09;
        } else {
            price = 0.7;
            switch (dayPart){
                case "day": price += distance * 0.79; break;
                case "night": price += distance * 0.9; break;
                default: break;
            }
        }

        System.out.printf("%.2f", price);
    }
}