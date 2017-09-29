import java.util.Scanner;

public class p04_2x2SuaresInMatrix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] userIn = input.nextLine().split(" ");
        int rows = Integer.parseInt(userIn[0]);
        int cols = Integer.parseInt(userIn[1]);
        char[][] matx = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            char[] line = input.nextLine()
                    .replaceAll("\\s+", "").toCharArray();
            matx[row] = line;
        }
        int subMatrixCount = 0;
        for (int row = 0; row < rows - 1; row++) {
            for (int col = 0; col < cols - 1; col++) {
                char currentCh = matx[row][col];
                int count = countSubMatrix(matx, currentCh);
                if (count > subMatrixCount){
                    subMatrixCount = count;
                }
            }
        }
        System.out.println(subMatrixCount);
    }

    private static int countSubMatrix(char[][] matx, char currentCh) {
        int count = 0;
        for (int row = 0; row < matx.length - 1; row++) {
            for (int col = 0; col < matx[row].length - 1; col++) {
                if (matx[row][col] == currentCh
                        && matx[row][col + 1] == currentCh
                        && matx[row + 1][col] == currentCh
                        && matx[row + 1][col + 1] == currentCh){
                    count++;
                }
            }
        }
        return count;
    }
}