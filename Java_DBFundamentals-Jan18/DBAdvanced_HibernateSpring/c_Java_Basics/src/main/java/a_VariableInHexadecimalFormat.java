import java.util.Scanner;

public class a_VariableInHexadecimalFormat {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        String hexNum = scann.nextLine();
        int num = Integer.parseInt(hexNum, 16);
        System.out.println(num);
    }
}

