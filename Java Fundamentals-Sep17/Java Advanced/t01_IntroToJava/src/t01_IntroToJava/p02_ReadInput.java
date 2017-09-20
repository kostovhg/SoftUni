package t01_IntroToJava;

import java.util.Scanner;

public class p02_ReadInput {
    public static void main(String[] args){
        // Add scanner
        Scanner input = new Scanner(System.in);
        // Read two strings from the first line
        String firstWord = input.next();
        String secondWord = input.next();
        // Read three integers which may be on multiple lines
        int firstInt = input.nextInt();
        int secondInt = input.nextInt();
        int thirdInt = input.nextInt();
        // Skip to the line end
        input.nextLine();
        // A string from the next line
        String thirdWord = input.nextLine();

        int sum = firstInt + secondInt + thirdInt;

        System.out.println(firstWord + " " + secondWord + " " + thirdWord + " " + sum);
    }
}
