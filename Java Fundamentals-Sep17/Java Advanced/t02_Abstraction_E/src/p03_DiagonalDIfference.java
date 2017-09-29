import java.util.Scanner;

public class p03_DiagonalDIfference {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int matxSize = Integer.parseInt(input.nextLine());
        int[][] matx = new int[matxSize][matxSize];
        int primDiag = 0;
        int secdDiag = 0;
        for (int row = 0; row < matxSize; row++) {
            for (int col = 0; col < matxSize; col++) {
                matx[row][col] = input.nextInt();
                if (row == col){
                    primDiag += matx[row][col];
                }
                if (col == matxSize - 1 - row){
                    secdDiag += matx[row][col];
                }
            }
            input.nextLine();
        }

        System.out.println(Math.abs(primDiag - secdDiag));
    }
}