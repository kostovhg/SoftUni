import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p11_StringMatrixRotation {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String rotation = sc.nextLine();
        int deg = Integer.
                parseInt(rotation.
                        substring(rotation.indexOf('(') + 1,
                                rotation.indexOf(')')));
        int times = (deg % 360) / 90;
        char[][] matx = readStrMatxAsList();
        PrintRotatedMatrix(matx, times);
    }

    private static void PrintRotatedMatrix(char[][] matx, int times) {
        switch(times){
            case 0:
                for (int row = 0; row < matx.length; row++) {
                    System.out.println(String.valueOf(matx[row]));
                }
                break;
            case 1:
                for (int col = 0; col < matx[0].length; col++) {
                    for (int row = matx.length - 1; row >= 0; row--) {
                        System.out.print(matx[row][col]);
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int row = matx.length - 1; row >= 0; row--) {
                    for (int col = matx[row].length - 1; col >= 0 ; col--) {
                        System.out.print(matx[row][col]);
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int col = matx[0].length - 1; col >= 0 ; col--) {
                    for (int row = 0; row < matx.length; row++) {
                        System.out.print(matx[row][col]);
                    }
                    System.out.println();
                }
                break;
        }
    }

    public static char[][] readStrMatxAsList() {
        List<String> input = new ArrayList<>();
        String line;
        while (!(line = sc.nextLine()).equals("END")) {
            input.add(line);
        }
        int maxLen = 0;
        for (int i = 0; i < input.size(); i++){
            if(maxLen < input.get(i).length())
                maxLen = input.get(i).length();
        }
        char[][] matx = new char[input.size()][maxLen];
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < maxLen; col++) {
                if(col < input.get(row).length()) {
                    matx[row][col] = input.get(row).charAt(col);
                } else {
                    matx[row][col] = ' ';
                }
            }
        }
        return matx;
    }
}