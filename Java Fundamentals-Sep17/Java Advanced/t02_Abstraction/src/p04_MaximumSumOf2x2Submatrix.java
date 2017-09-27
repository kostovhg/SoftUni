import java.util.Scanner;

public class p04_MaximumSumOf2x2Submatrix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Find submatrix of size 2x2 with max sum of elements in a  bigger matrix
         */

        // read input for matrix size on one line as "<rows>, <columns>"
        String[] matxSize = input.nextLine().split(", ");
        // assign rows and cols counts
        int rows = Integer.parseInt(matxSize[0]);
        int cols = Integer.parseInt(matxSize[1]);
        // initialize a value to keep  the maximum sum
        long maxSubMatxSum = Long.MIN_VALUE;
        // initialize variables to keep submatrix first element
        int rowIndex = 0;
        int colIndex = 0;
        // initialize empty matrix of corresponding type
        int[][] matx = new int[rows][cols];
        // loop trough matrix and assign members
        for (int row = 0; row < rows; row++) {
            String[] line = input.nextLine().split(", ");
            for (int col = 0; col < cols; col++) {
                matx[row][col] = Integer.parseInt(line[col]);
            }
        }
        // loop again trough all submatrix
        for (int row = 0; row < matx.length - 1; row++) {
            for (int col = 0; col < matx[row].length - 1; col++) {
                // initialize and assign current submatrix sum
                int tempSum = matx[row][col] + matx[row][col +1] +
                        matx[row + 1][col] + matx[row + 1][col + 1];
                // if current sum is bigger than previous maximum sum
                if (tempSum > maxSubMatxSum){
                    // save coordinates of first member of submatrix
                    rowIndex = row;
                    colIndex = col;
                    // update maximum sum for submatrix
                    maxSubMatxSum = tempSum;
                }
            }
        }
        // print 2x2 sub matrix and the maximum sum
        System.out.println(matx[rowIndex][colIndex] + " " + matx[rowIndex][colIndex + 1]);
        System.out.println(matx[rowIndex + 1][colIndex] + " " + matx[rowIndex + 1][colIndex + 1]);
        System.out.println(maxSubMatxSum);
    }
}