package t01_IntroToJava;

import java.util.Scanner;

public class p07_Numbers0to9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            System.out.printf("Number: %d%n", i);
        }
    }
}