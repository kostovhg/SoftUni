import java.util.ArrayDeque;
import java.util.Scanner;

public class p04_TruckTour {
    private static ArrayDeque<Integer> indexes;

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int rows = Integer.parseInt(scann.nextLine());

        indexes = new ArrayDeque<>();
        Long[] pumps = new Long[rows];
        Long[] distance = new Long[rows];

        for (int i = 0; i < rows; i++) {
            String[] input = scann.nextLine().split("\\s+");
            pumps[i] = (Long.parseLong(input[0]));
            distance[i] = (Long.parseLong(input[1]));
            indexes.add(i);
        }

        for (int index = 0; index < rows; index++) {
            boolean cycle = true;
            int cIndex = indexes.poll();
            indexes.add(cIndex);
            Long fuel = 0L;
            for (int i = 0; i <= rows; i++) {
                fuel += pumps[(i + cIndex) % rows];
                fuel -= distance[(i + cIndex) % rows];
                if(fuel < 0){
                    cycle = false;
                    break;
                }
            }
            if (cycle){
                System.out.println(cIndex);
                return;
            }
        }

    }
}