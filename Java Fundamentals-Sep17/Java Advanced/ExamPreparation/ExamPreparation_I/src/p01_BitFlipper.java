import java.math.BigInteger;
import java.util.Objects;
import java.util.Scanner;

public class p01_BitFlipper {
    /* Code rewritten for java from:
    dim4o -> https://softuni.bg/users/profile/show/dim4o
    pastebin -> https://pastebin.com/u4s0LSqZ
    softuniforum -> https://softuni.bg/forum/2718/exam-problems-csharp-basics-problem-5-bit-flipper-14-april-2014-evening
     */
    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);
        String inputNumber = scann.nextLine();

        /*BigInteger mask = BigInteger.valueOf(7L);
        mask = mask.shiftLeft(61);*/
        BigInteger mask = new  BigInteger("e000000000000000", 16);
        BigInteger num = new BigInteger(inputNumber);

        // System.out.printf("%16s -> %s%n", "mask length", mask.toString(2).length());
        // System.out.printf("%16s -> %s%n", "binary mask",mask.toString(2));
        // System.out.printf("%16s -> %s%n", "binary num",num.toString(2));

        while (mask.compareTo(BigInteger.valueOf(7L)) >= 0){
            //System.out.printf("%16s -> %s%n", "mask & num", mask.and(num).toString(2));
            boolean treeConsecutiveOnes = Objects.equals(num.and(mask), mask);
            boolean treeConsecutiveZeroes = Objects.equals(num.and(mask), BigInteger.ZERO);
            if(treeConsecutiveOnes || treeConsecutiveZeroes){
                if(treeConsecutiveOnes){
                    //num &= (~mask)
                    num = num.andNot(mask);
                    //System.out.printf("%16s -> %s%n", "num &= (~mask)", num.toString(2));
                } else {
                    // num |= mask
                    num = num.or(mask);
                    //System.out.printf("%16s -> %s%n", "num |= mask", num.toString(2));
                }
                // move mask right
                mask = mask.shiftRight(3);
                //System.out.printf("%16s -> %s%n", "mask >> 3 ", mask.toString(2));
            } else {
                mask = mask.shiftRight(1);
                //System.out.printf("%16s -> %s%n","mask >> 1", mask.toString(2));
            }
        }
        System.out.println(num);
    }
}
