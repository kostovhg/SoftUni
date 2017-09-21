package t01_IntroToJava_E;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p05_OddAndEvenPairs {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        From given array check consecutive pairs and prints if
        both are odd/even or not
         */

        // read input line as string
        String line = input.nextLine();
        // create new list
        List<Integer> list = new ArrayList<Integer>();
        // populate the list
        for (String s : line.split("\\s")) {
            list.add(Integer.parseInt(s));
        }
        // if length is not even, exit with message
        // list.size() % 2 != 0 or
        if((list.size() & 1) == 1){
            System.out.println("invalid length");
            return;
        }
        // convert list to array
        list.toArray();
        // loop trough the array with step of 2
        // and print the results
        for (int i = 0; i < list.size() - 1; i += 2) {
            System.out.printf("%d, %d -> %s%n", list.get(i),
                    list.get(i + 1), checkPair(list.get(i), list.get(i + 1)));
        }
    }

    private static String checkPair(Integer a, Integer b) {

        /*
        Method to check if two numbers are both even/odd or different
         */
        if ( (a & 1) == (b & 1) ) {
            if ((a & 1) == 0){
                return "both are even";
            } else {
                return "both are odd";
            }
        }
        return "different";
    }
}