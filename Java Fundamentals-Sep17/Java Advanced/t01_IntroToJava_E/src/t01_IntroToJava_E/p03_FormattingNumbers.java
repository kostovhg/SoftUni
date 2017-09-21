package t01_IntroToJava_E;

import java.util.Scanner;

public class p03_FormattingNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Read one integer (between 0 and 500) and two floating point numbers
        print them in 4 columns of 10 symbols separated with '|'
        - first column with hexadecimal, left aligned value of the integer
        - second column with binary form value padded with zeroes of the integer
        - third column with right aligned first floating number with 2 digits after decimal point
        - forth column with left aligned second floating number with 3 digits after decimal point
         */
        int aInt = input.nextInt();
        // add 1 as leading bit, convert it to string, remove leading bit
        // to have 0 padded binary representation
        String aBit = Integer.toBinaryString( (1 << 10) | aInt).substring(1);
        // other method with converting the integer to binary string, parse it to Integer
        // format this integer with leading zeroes
        // String aBits = String.format("%010d", Integer.parseInt(Integer.toBinaryString(aInt)));
        double bDob = input.nextDouble();
        double cDob = input.nextDouble();

        System.out.printf("|%-10X|%s|%10.2f|%-10.3f|", aInt, aBit, bDob, cDob);

    }
}