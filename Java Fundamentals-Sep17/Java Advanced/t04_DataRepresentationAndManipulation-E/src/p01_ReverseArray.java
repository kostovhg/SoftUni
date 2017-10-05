import java.util.Arrays;
import java.util.Scanner;

public class p01_ReverseArray {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] arr = scan.nextLine().split("\\s+");
        reverse(arr, 0);
        for (String str :
                arr) {
            System.out.print(str + " ");
        }
    }

    private static void reverse(String[] arr, int bottom) {
        if(bottom >= arr.length / 2) return;
        String tmp = arr[bottom];
        arr[bottom] = arr[arr.length - 1 - bottom];
        arr[arr.length - 1 - bottom] = tmp;
        reverse(arr, bottom + 1);

    }
}