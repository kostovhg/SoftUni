import java.util.Arrays;
import java.util.Scanner;

public class p04_RecursiveFactorial {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());

        long result = FindFactorial(num);
        System.out.println(result);

    }

    private static long FindFactorial(int num) {
        if (num==0) return 1;
        return num * FindFactorial(num - 1);
    }
}