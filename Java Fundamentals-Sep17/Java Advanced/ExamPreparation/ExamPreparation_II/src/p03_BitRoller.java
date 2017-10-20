import java.util.Scanner;

public class p03_BitRoller {

    private static Scanner scanner = new Scanner(System.in);

    /* we are going to split the bites of left and right side, take out the fixed bit
    join them again, rotate them few times, split the rotated one, put the fixed bit
     */

    public static void main(String[] args) {
        int num = Integer.parseInt(scanner.nextLine());
        int pos = Integer.parseInt(scanner.nextLine());
        int rot = Integer.parseInt(scanner.nextLine());
        print("num", num);
        int mask = 0x3ffff;//524287; mask with 19-th 1s. to be cut and shifted

        print("mask", mask);
        int fixed = (num >> pos) & 1; // take the fixed bit
        print("fixed", fixed);
        int right = num & (mask >> (18 - pos )); // shift all bit numbers till fixed one
        print("right",right);
        int left = num >> pos + 1; // clear all bits before fixed one
        print("left", left);
        int temp = left << pos; // copy to temp left side
        temp |= right; // copy to temp right side
        print("temp", temp);
        for (int i = 0; i < rot; i++) { // rotate joined
            int l = temp & 1;
            temp = temp >> 1;
            if(l == 1) temp |= 0x20000; // if last bit is 1, put at first of 19th a 1.
            //0x2000; // 131072
            print("temp(" + i + ")", temp);
        }

        right = temp & (mask >> 18 - pos); // to split, copy all rotated before fixed position
        left = temp >> pos; // copy all rotated after fixed position
        left = left << 1; // open one slot for
        left |= fixed; // our fixed bite and insert it
        left = left << pos; // shift to fill all , as leaving 0s after fixed
        left |= right; // copy right part to the left one
        print("new right", right);
        System.out.println(left);


    }
    private static void print(String str, int num){
        /*System.out.printf("%10s -> ", str);
        System.out.println(Integer.toString(num, 2));*/
    }
}
