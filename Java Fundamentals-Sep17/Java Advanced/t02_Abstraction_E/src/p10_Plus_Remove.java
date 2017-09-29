import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p10_Plus_Remove {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        /*
        TODO: try with 3D matrix, holding in third dimension the boolean matrix
         */
        List<char[]> initial = readStrMatxAsList();
        boolean[][] mask = FillTheMask(initial);

        List<StringBuilder> output = new ArrayList<>();
        for (int i = 0; i < initial.size(); i++) {
            output.add(new StringBuilder());
            for (int ch = 0; ch < initial.get(i).length; ch++) {
                if(!mask[i][ch]) output.get(i).append(initial.get(i)[ch]);
            }
            System.out.println(output.get(i));
        }

    }

    private static boolean[][] FillTheMask(List<char[]> initial) {
        boolean[][] mask = new boolean[initial.size()][];
        for (int row = 0; row < initial.size(); row++) {
            mask[row] = new boolean[initial.get(row).length];
        }
        for (int row = 1; row < initial.size() - 1; row++) {
            for (int col = 1; col < initial.get(row).length - 1; col++) {
                if(CheckForPlus(initial, row, col)){
                    mask[row][col] = true;
                    mask[row][col - 1] = true;
                    mask[row][col + 1] = true;
                    mask[row - 1][col] = true;
                    mask[row + 1][col] = true;
                }
            }
        }
        return mask;
    }

    private static boolean CheckForPlus(List<char[]> initial, int row, int col) {
        try {
            char ch = Character.toLowerCase(initial.get(row)[col]);
            if (Character.toLowerCase(initial.get(row - 1)[col]) == ch &&
                    Character.toLowerCase(initial.get(row)[col - 1]) == ch &&
                    Character.toLowerCase(initial.get(row)[col + 1]) == ch &&
                    Character.toLowerCase(initial.get(row + 1)[col]) == ch) return true;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
        return false;
    }

    public static List<char[]> readStrMatxAsList() {
        List<char[]> matx = new ArrayList<char[]>();
        String line;
        while (!(line = sc.nextLine()).equals("END")) {
            matx.add(line.toCharArray());
        }
        return matx;
    }
}