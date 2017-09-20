package t01_IntroToJava;

import java.util.Scanner;

public class p11_ModifyABit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int num = input.nextInt();
        int pos = input.nextInt();
        int val = input.nextInt();
        int result = num;
        if ( val == 0){
            int mask = ~(1 << pos);
            result = num & mask;
        } else {
            int mask = 1 << pos;
            result = num | mask;
        }
        System.out.println(result);
    }
}