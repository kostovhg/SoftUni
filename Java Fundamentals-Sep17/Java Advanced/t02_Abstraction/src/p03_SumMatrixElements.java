import java.util.Scanner;

public class p03_SumMatrixElements {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] matxSize = input.nextLine().split(", ");
        int rows = Integer.parseInt(matxSize[0]);
        int cols = Integer.parseInt(matxSize[1]);
        long sum = 0L;
        int[][] matx = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            String[] line = input.nextLine().split(", ");
            for (int col = 0; col < cols; col++) {
                matx[row][col] = Integer.parseInt(line[col]);
                sum += matx[row][col];
            }
        }
        System.out.println(rows);
        System.out.println(cols);
        System.out.println(sum);
    }
}