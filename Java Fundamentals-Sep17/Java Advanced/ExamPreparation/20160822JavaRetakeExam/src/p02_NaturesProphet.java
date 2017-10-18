import java.util.Scanner;

public class p02_NaturesProphet {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);
        int[][][] garden = new int[rows][cols][2];

        while(!"Bloom".equalsIgnoreCase(
                (input = scan.nextLine().split(" "))[0]
        )){
            int row = Integer.parseInt(input[0]);
            int col = Integer.parseInt(input[1]);
            garden[row][col][1] = 1;
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if(garden[row][col][1] == 1){
                    for (int i = 0; i < cols; i++) {
                        garden[row][i][0]++;
                    }
                    for (int i = 0; i < rows; i++) {
                        if(i== row) continue;
                        garden[i][col][0]++;
                    }
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(garden[row][col][0] +" ");
            }
            System.out.println();
        }
    }
}
