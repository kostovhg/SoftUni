import java.util.Scanner;

public class p02_MatrixOfPalindromes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] userIn = input.nextLine().split(" ");
        int rows = Integer.parseInt(userIn[0]);
        int cols = Integer.parseInt(userIn[1]);
        String[][] matx = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matx[row][col] = palindrome(row, col);
            }
        }

        for (int row = 0; row < rows; row++) {
            System.out.println(String.join(" ", matx[row]));
        }
    }

    public static String palindrome(int r, int c){
        char ch = (char)('a' + r);
        return String.valueOf(new char[]
                {ch, (char)(ch + c ), ch});
    }
}