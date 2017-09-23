package t01_IntroToJava_E;

import java.util.Scanner;

public class p13_BlurFilter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int blur = Integer.parseInt(input.nextLine());
        String[] matSize = input.nextLine().split("\\s+");

        int rows = Integer.parseInt(matSize[0]);
        int cols = Integer.parseInt(matSize[1]);

        long[][] mat = new long[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] rowMembers = input.nextLine().split("\\s+");
            for (int col = 0; col < cols; col++) {
                mat[row][col] = Long.parseLong(rowMembers[col]);
            }
        }

        String[] target = input.nextLine().split("\\s+");
        int targetRow = Integer.parseInt(target[0]);
        int targetCol = Integer.parseInt(target[1]);

        int left = Math.max(targetCol - 1, 0);
        int right = Math.min(targetCol + 1, cols - 1);
        int top = Math.max(targetRow - 1, 0);
        int bottom = Math.min(targetRow + 1, rows - 1);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (left <= col && col <= right && top <= row && row <= bottom) {
                    System.out.printf("%d ", mat[row][col] + blur);
                } else {
                    System.out.printf("%d ", mat[row][col]);
                }
            }
            System.out.println();
        }
    }
}