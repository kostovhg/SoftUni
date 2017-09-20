package t01_IntroToJava;

import java.math.BigInteger;
import java.util.Scanner;

public class p08_ProductOfNumbersNtoM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Integer fromN = input.nextInt();
        Integer tillM = input.nextInt();
        BigInteger product = new BigInteger("1");
        for (int i = fromN; i <= tillM; i++) {
            product = product.multiply(BigInteger.valueOf(i));
        }
        System.out.printf("product[%d..%d] = %d", fromN, tillM, product);
    }
}