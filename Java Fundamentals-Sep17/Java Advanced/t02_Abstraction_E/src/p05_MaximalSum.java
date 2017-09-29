import java.util.Scanner;

public class p05_MaximalSum {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int[][] matx = readIntMatrix(input.nextLine());
        int subRowIndex = 0;
        int subColIndex = 0;
        long subMatxSum = 0;

        for (int row = 0; row < matx.length - 2; row++) {
            for (int col = 0; col < matx[row].length - 2; col++) {
                long currentSum = sub3x3MatxSum(matx, row, col);
                if (subMatxSum < currentSum){
                    subMatxSum = currentSum;
                    subRowIndex = row;
                    subColIndex = col;
                }
            }
        }
        System.out.printf("Sum = %d%n", subMatxSum);
        PrintMatrix(matx, subRowIndex, subColIndex, 3, 3);
    }

    private static long sub3x3MatxSum(int[][] matx, int row, int col) {
        long sum = 0;
        for (int r = row; r < row + 3; r++) {
            for (int c = col; c < col + 3; c++) {
                sum += matx[r][c];
            }
        }
        return sum;
    }

    private static void PrintMatrix(int[][] matx, int startRow, int startCol, int rows, int cols) {
        for (int row = startRow; row < startRow + rows; row++) {
            for (int col = startCol; col < startCol + cols; col++) {
                System.out.print(matx[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] readIntMatrix(String matxSize){
        String[] size = matxSize.split("\\s+|,");
        int rows = Integer.parseInt(size[0]);
        int cols;
        if(size.length == 1){
            cols = rows;
        } else {
            cols = Integer.parseInt(size[1]);
        }

        int[][] matx = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matx[row][col] = input.nextInt();
            }
            input.nextLine();
        }

        return matx;
    }
}