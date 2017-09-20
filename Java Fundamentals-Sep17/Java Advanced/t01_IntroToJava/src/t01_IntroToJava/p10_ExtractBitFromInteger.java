package t01_IntroToJava;

import java.util.Scanner;

public class p10_ExtractBitFromInteger {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Integer num = input.nextInt();
        Integer pos = input.nextInt();

        System.out.print((num >> pos) & 1);
    }
}