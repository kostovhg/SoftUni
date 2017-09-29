import java.util.Scanner;

public class p08_MatrixShuffling {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] size = sc.nextLine().split("\\s+|,");
        int rows = Integer.parseInt(size[0]);
        int cols = Integer.parseInt(size[1]);
        String[][] matx = readStrMatx(rows, cols);

        int[] input;
        while((input = ReadCommand())[0] != -1){
            if (!Verify(input, rows, cols)) {
                System.out.println("Invalid input!");
                continue;
            }

            ExecuteSwap(matx, input);
            PrintStrMatrix(matx);
        }

    }

    private static void PrintStrMatrix(String[][] matx) {
        for (int i = 0; i < matx.length; i++) {
            System.out.println(String.join(" ", matx[i]));
        }
    }

    private static void ExecuteSwap(String[][] matx, int[] input) {
        String temp = matx[input[3]][input[4]];
        matx[input[3]][input[4]] = matx[input[1]][input[2]];
        matx[input[1]][input[2]] = temp;
    }

    private static boolean Verify(int[] input, int r, int c) {
        if(input[0] == 0) return false;
        if(r <= input[1] || r <= input[3] || input[1] < 0 || input[3] < 0) return false;
        if(c <= input[2] || c <= input[4] || input[2] < 0 || input[4] < 0) return false;
        else return true;
    }

    public static int[] ReadCommand(){
        int[] command = {1, 0, 0, 0, 0};
        String[] input = sc.nextLine().split("\\s+|,");
        if (input[0].equals("END")){
            command[0] = -1;
            return command;
        }
        if (!input[0].equals("swap")){
            command[0] = 0;
            return command;
        }
        for (int i = 1; i < input.length; i++) {
            // if we have invalid strings for coordinates!
            try{
                command[i] = Integer.parseInt(input[i]);
            } catch (Exception e) {
                command[0] = 0;
            }
        }

        return command;
    }

    public static String[][] readStrMatx(int rows, int cols){
        String[][] matx = new String[rows][cols];
        for (int row = 0; row < rows; row++) {
            matx[row] = sc.nextLine().split("\\s+|,");
        }

        return matx;
    }
}