package t01_IntroToJava;

import java.util.Scanner;

public class p05_Greeting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String firstName = input.nextLine();
        if (firstName.isEmpty()){
            firstName = "*****";
        }
        String familyName = input.nextLine();
        if (familyName.isEmpty()){
            familyName = "*****";
        }

        System.out.println("Hello, " + firstName + " " + familyName + "!");
    }
}