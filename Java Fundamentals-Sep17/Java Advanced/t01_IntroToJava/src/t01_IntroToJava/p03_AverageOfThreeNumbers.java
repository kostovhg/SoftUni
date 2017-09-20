package t01_IntroToJava;

import java.util.Scanner;

public class p03_AverageOfThreeNumbers {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        // Read three double numbers
        double first = input.nextDouble();
        double second = input.nextDouble();
        double third = input.nextDouble();

        System.out.println(String.format("%.2f", (first + second + third) / 3));
    }
}
