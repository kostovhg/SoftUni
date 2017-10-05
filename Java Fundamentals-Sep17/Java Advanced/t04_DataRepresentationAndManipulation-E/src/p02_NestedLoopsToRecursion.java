import java.util.Scanner;

public class p02_NestedLoopsToRecursion {
    private static Scanner sc = new Scanner(System.in);
    static int count;
    static int[] loops;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //int count = Integer.parseInt(scan.nextLine());
        count = Integer.parseInt(scan.nextLine());
        loops = new int[count];

        nestedLoops(0);
    }

    private static void nestedLoops(int curr) {
        if(curr == count) {
            PrintLoops();
            return;
        }
        for (int i = 1; i <= count; i++) {
            loops[curr] = i;
            nestedLoops(curr + 1);
        }

    }

    private static void PrintLoops() {
        for (int i = 0; i < count; i++) {
            System.out.print(loops[i] + " ");
        }
        System.out.println();
    }
}