package t01_IntroToJava_E;

import java.text.DecimalFormat;
import java.util.Scanner;

public class p01_RectangleArea {
    public static void main(String[] args) {
        /*
        Read from the console two integers as a sides of a rectangle
        and return rectangle area formatted to the second digit after
        decimal separator
         */
        Scanner input = new Scanner(System.in);

        /*int sideA = input.nextInt();
        int sideB = input.nextInt();
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(sideA * sideB));*/
        double sideA = input.nextDouble();
        double sideB = input.nextDouble();
        System.out.printf("%.2f", sideA * sideB);
    }

}