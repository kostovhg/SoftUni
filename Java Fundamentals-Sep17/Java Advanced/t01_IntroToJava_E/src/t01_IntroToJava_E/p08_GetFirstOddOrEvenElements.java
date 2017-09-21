package t01_IntroToJava_E;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p08_GetFirstOddOrEvenElements {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Return first N odd/even elements from a collection
         */
        // read collection
        String[] line = input.nextLine().split("\\s+");
        // Skip "Get"
        input.next();
        // read the count
        int count = Integer.parseInt(input.next());
        // read even/odd - if input is "even", variable value is true
        int evenOrOdd = input.next().equals("even") ? 0 : 1;
        // convert it to integer array
        List<String> list = new ArrayList<String>();
        // now this list will be the result
        // it will got trough all members of the input array "line"
        // and fill until the given count is reached or valid elements are depleted
        for (int i = 0; i < line.length && count > 0; i++) {
            // check if integer value of the current member of string array is
            // even or odd in dependency of the boolean variable "even", if not - skip
            if((Integer.parseInt(line[i]) & 1) != evenOrOdd)
                continue;
            list.add(line[i]);
            // reduce the remaining members to be filled in the list
            count--;
        }
        // Print result
        System.out.println(String.join(" ", list));
    }
}