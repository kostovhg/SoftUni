import java.util.Scanner;

public class p07_CollectTheCoins {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        char[][] board = FillCharMatrix();
        char[] movement = sc.nextLine().toLowerCase().toCharArray();
        int row = 0;
        int col = 0;
        int coins = 0;
        int walls = 0;
        for (char direction :
                movement) {
            try
            {
                switch (direction) {
                    case '>':
                        col++;
                        break;
                    case '<':
                        col--;
                        break;
                    case '^':
                        row--;
                        break;
                    case 'v':
                        row++;
                        break;
                }
                if(board[row][col] == '$') coins++;
            }
            catch (ArrayIndexOutOfBoundsException exeption)
            {
                walls++;
                switch (direction) {
                    case '>':
                        col--;
                        break;
                    case '<':
                        col++;
                        break;
                    case '^':
                        row++;
                        break;
                    case 'v':
                        row--;
                        break;
                }
            }
        }
        System.out.printf("Coins = %d%n", coins);
        System.out.printf("Walls = %d%n", walls);
    }

    public static char[][] FillCharMatrix(){
        char[][] arr = new char[4][];
        for (int i = 0; i < 4; i++) {
            arr[i] = sc.nextLine().toCharArray();
        }
        return arr;
    }
}