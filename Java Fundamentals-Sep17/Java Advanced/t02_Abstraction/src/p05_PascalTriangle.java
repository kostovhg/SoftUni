import java.math.BigInteger;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class p05_PascalTriangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Plot Pascal triangle of given size
         */
        // take the size of the triangle (max 100)
        int triangleSize = Integer.parseInt(input.nextLine());
        // Make jagged array for the triangle of type BigInteger
        BigInteger[][] pascTriangle = new BigInteger[triangleSize][];
        // Create the first element of the triangle
        pascTriangle[0] = new BigInteger[] {BigInteger.ONE};
        for (int row = 1; row < triangleSize; row++) {
            // create next row with length +1
            pascTriangle[row] = new BigInteger[row + 1];
            // for current row create first element = 1
            pascTriangle[row][0] = BigInteger.ONE;
            // find the middle of the row
            int middle = (pascTriangle[row].length - 1) /2;
            // loop to the end of the current row
            for (int col = 1; col < pascTriangle[row].length; col++) {
                // if the index is less or equal to the middle index
                if (col <= middle){
                    // sum the above row members and save result at current possition
                    pascTriangle[row][col] = (pascTriangle[row - 1][col - 1]).add(pascTriangle[row - 1][col]);
                } else {
                    // else, copy forward previous members
                    // "row - col" is in fact "(length - 1) - col" or "(last index) - current iteration"
                    pascTriangle[row][col] = pascTriangle[row][row  - col];
                }
            }
        }
        // print the triangle
        for (int row = 0; row < pascTriangle.length; row++) {
            for (int col = 0; col < pascTriangle[row].length; col++) {
                System.out.print(pascTriangle[row][col] + " ");
            }
            System.out.println();
        }
    }
}