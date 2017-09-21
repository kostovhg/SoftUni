package t01_IntroToJava_E;

import java.util.Scanner;

public class p10_X_Bits {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /* Find count of X_bits in array of integers
        X_bit is {{1 0 1}, {0 1 0}, {1 0 1}}
         */

        // create empty array
        int[] arr = new int[8];
        /*
        TODO: not working
        add temporal array which holds possible x_bits in the next roll
        to be skipped for optimization
        so all checked positions are allowed to be used
        if in previous row in the same poss there was x_bit,
        it is not possible on this roll to have x_bit.
        so switch "nextRow" bit at pos to 0;
        */
        // int nextRow = -1;
        // counter
        int count = 0;
        // fill up the array
        for (int i = 0; i < 8; i++) {
            arr[i] = Integer.parseInt(input.nextLine());
        }
        // loop trough the array from 0 to size - 2
        for (int row = 0; row < 6; row++) {
            // loop trough bytes from third most significant to pos 0
            // int pos = mostSignificantBit(arr[row]) - 2;
            for (int pos = 29; pos >= 0 ; pos--) {
                // 5 is 101, 2 is 010 (10) and they should be
                // like
                // 101 , which is 5
                // 010 , which is 2
                // 101 , which is 5
                // slip bits of the int like 1010101 >> 2 which is 10101
                // And (&) with 7 (111 - mask) to use only 3 bites and skip higher power bits
                // compare it with 5 and 2
                if(5 == (7 & (arr[row] >> pos)) &&
                       5 == (7 & (arr[row + 2] >> pos)) &&
                        2 == (7 & (arr[row + 1] >> pos))){
                    count++;
                    // skipping next iteration along the row
                    // as it is not possible to contain x_bit
                    pos--;
                    // skip the same iteration on the next row
                    // as shifting th bit of "nextRow" to 0
                    //nextRow &= ~(1 << pos);
                } else {
                    // permit next roll check
                    //nextRow |= 1 << pos;
                }
            }
        }
        System.out.println(count);
    }
    // taken from https://stackoverflow.com/questions/9117793/bitwise-most-significant-set-bit
    // author: Michael McGowan
    // not so useful as the time for finding it is almost the same as check them all
/*    private static int mostSignificantBit(int myInt){
        int mask = 1 << 30;
        for(int bitIndex = 30; bitIndex >= 0; bitIndex--){
            if((myInt & mask) != 0){
                return bitIndex;
            }
            mask >>>= 1;
        }
        return -1;
    }*/
}