package t01_IntroToJava_E;

import java.util.Scanner;

public class p06_HitTheTarget {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Receive a number (target) and print all sums and differences
        between numbers from 1 to 20 that gives that number (target)
         */
        Integer target = input.nextInt();
        Integer x = 1;
        Integer y = target - x;
        // loop from 1 to 20
        for (; x < 21; x++) {
            // calculate corresponding y in x + y = target
            y = target - x;
            // skip every result, where y is above 20 or equal to 0
            if(Math.abs(y) > 20 || y == 0 ) continue;
            // if y is positive we have sum of x and y
            // if y is negative and target positive, we have difference
            // if y and target are negative, x < y in x - y = target
            if(y > 0 ){
                System.out.printf("%d + %d", x,  y);
            } else if (target > 0){
                System.out.printf("%d - %d", x, -y);
            } else {
                System.out.printf("%d - %d", x, Math.abs(target) + x);
            }
            System.out.println(" = " + target);
        }
    }
}