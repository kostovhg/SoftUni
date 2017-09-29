import java.util.Collections;
import java.util.Scanner;

public class p06_SequenceInMatrix {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String[][] matx = readStrMatx();

        /*
        I have tried so manny options for so long time, and this one is working, so
        I not going to optimize or clean the code.
         */

        int longestSeq = 1;
        String repString = "";
        for (int row = 0; row < matx.length - 1; row++) {
            for (int col = 0; col < matx[row].length - 1; col++) {
                int tempDiag = CheckFirstDiag(matx, row, col);
                if (tempDiag >= longestSeq){
                    longestSeq = tempDiag;
                    repString = matx[row][col];
                }
            }
        }
        for (int row = 0; row < matx.length - 1; row++) {
            for (int col = 1; col < matx[row].length - 1; col++) {
                int tempSecDiag = CheckSecondDiag(matx, row, col);
                if (tempSecDiag >= longestSeq){
                    longestSeq = tempSecDiag;
                    repString = matx[row][col];
                }
            }
        }
        for (int row = 0; row < matx.length; row++) {
            for (int col = 0; col < matx[row].length - 1; col++) {
                int line = CheckRow(matx, row, col);
                if (line >= longestSeq){
                    longestSeq = line;
                    repString = matx[row][col];
                }
            }
        }
        for (int row = 0; row < matx.length - 1; row++) {
            for (int col = 0; col < matx[row].length - 1; col++) {
                int colum  = CheckColum(matx, row, col);
                if (colum >= longestSeq){
                    longestSeq = colum;
                    repString = matx[row][col];
                }
            }
        }
        System.out.println(String.join(", ", Collections.nCopies(longestSeq, repString)));
    }

    private static int CheckSeq(String[][] matx, int r, int c) {
        int[] len = {1, 1, 1, 1};
        String str = matx[r][c];
        // check first diagonal
        while(r + 1 < matx.length && c + 1 < matx[r].length && str.equals(matx[r + 1][c + 1])){
            len[0]++;
            r++;
            c++;
        }
        // check second diagonal
        while((r - 1 > 0) && (c - 1 > 0) && str.equals(matx[r - 1][c - 1])){
            len[1]++;
            r--;
            c--;
        }
        // check lines
        while(c + 1 < matx[r].length && str.equals(matx[r][c + 1])){
            len[2]++;
            c++;
        }
        // check columns
        while(r + 1 < matx.length && str.equals(matx[r + 1][c])){
            len[3]++;
            r++;
        }
        int result = 1;
        for (int i = 0; i < 4; i++) {
            if(len[i] > result) result = len[i];
        }
        return result;
    }

    private static int CheckColum(String[][] matx, int r, int c) {
        int len = 1;
        String str = matx[r][c];
        while(r + 1 < matx.length && str.equals(matx[r + 1][c])){
            len++;
            r++;
        }
        return len;
    }

    private static int CheckRow(String[][] matx, int r, int c) {
        int len = 1;
        String str = matx[r][c];
        while(c + 1 < matx[r].length && str.equals(matx[r][c + 1])){
            len++;
            c++;
        }
        return len;
    }

    private static int CheckSecondDiag(String[][] matx, int r, int c) {
        int len = 1;
        String str = matx[r][c];
        // Got trough diagonals
        while((r - 1 > 0) && (c - 1 > 0) && str.equals(matx[r - 1][c - 1])){
            len++;
            r--;
            c--;
        }
        return len;
    }

    private static int CheckFirstDiag(String[][] matx, int r, int c) {
        int len = 1;
        String str = matx[r][c];
        while(r + 1 < matx.length && c + 1 < matx[r].length && str.equals(matx[r + 1][c + 1])){
            len++;
            r++;
            c++;
        }
        return len;
    }

    public static String[][] readStrMatx(){
        String[] size = sc.nextLine().split("\\s+|,");
        int rows = Integer.parseInt(size[0]);
        int cols;
        if(size.length == 1){
            cols = rows;
        } else {
            cols = Integer.parseInt(size[1]);
        }

        String[][] matx = new String[rows][cols];
        for (int row = 0; row < rows; row++) {
            matx[row] = sc.nextLine().split("\\s+|,");
        }

        return matx;
    }

}