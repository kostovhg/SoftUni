
import java.util.Scanner;

public class e_IntegerToHexAndBinary {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        Integer inputNumber = Integer.parseInt(scann.nextLine());

        System.out.println(String.format("%X", inputNumber));
        System.out.println(Integer.toBinaryString(inputNumber));
        //System.out.println(Integer.toHexString(inputNumber));
    }
}
