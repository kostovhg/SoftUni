package t01_IntroToJava_E;

import java.math.BigInteger;
import java.util.Scanner;

public class p07_CharacterMultiplier {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Take two strings, and return sum of their character codes multiplied
        if length is different, just add character codes to the sum
         */

        String first = input.next();
        String second = input.next();
        // check length of the strings
        // if they are equal, it doesn't matter
        boolean firstLonger = first.length() > second.length();
        char[] shorter = (firstLonger ? second : first)
                .toCharArray();
        char[] longer = (firstLonger ? first : second)
                .toCharArray();
        System.out.println(charMultiplier(shorter, longer));
    }

    private static BigInteger charMultiplier(char[] shorter, char[] longer) {
        /*
        Loop trough longer string and multiply each char with its
        corresponding from the shorter. If corresponding is missing multiply by 1.
        eturn sum of those products.
         */
        BigInteger sum = BigInteger.valueOf(0);
        for (int i = 0; i < longer.length; i++) {
            sum = sum.add(BigInteger.valueOf(
                    longer[i] * ((i < shorter.length) ? shorter[i] : 1)));
        }
        return sum;
    }
}