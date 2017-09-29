import java.util.Scanner;

public class p01_FillTheMatrix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Fill a matrix in two different methods
         */

        String[] userIn = input.nextLine().split(", ");
        int matxSize = Integer.parseInt(userIn[0]);
        String method = userIn[1];

        int[][] matrix = fillTheMatrix(matxSize, method);

        // print
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] fillTheMatrix(int matxSize, String method) {
        int[][] matx = new int[matxSize][matxSize];
        int current = 1;
        if(method.equals("A")){
            for (int col = 0; col < matxSize; col++) {
                for (int row = 0; row < matxSize; row++) {
                    matx[row][col] = current;
                    current++;
                }
            }
        } else {
            for (int col = 0; col < matxSize; col++) {
                boolean down = (col % 2 == 0);
                for (int row = (down ? 0 : matxSize - 1); row < matxSize && row > -1; ) {
                    matx[row][col] = current;
                    current++;
                    row = (down) ? (row +1) : (row - 1);
                }
            }

        }

        return matx;
    }


}