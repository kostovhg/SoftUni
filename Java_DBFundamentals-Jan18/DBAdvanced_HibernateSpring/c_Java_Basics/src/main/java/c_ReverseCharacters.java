import java.util.Scanner;

public class c_ReverseCharacters {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(scann.nextLine());
        }
        System.out.println(sb.reverse());
    }
}
