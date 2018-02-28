import java.util.Scanner;

public class b_BooleanVariable {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        String stringBool = scann.nextLine();
        Boolean resultBool = Boolean.parseBoolean(stringBool);
        System.out.println(resultBool ? "Yes" : "No");

    }
}
