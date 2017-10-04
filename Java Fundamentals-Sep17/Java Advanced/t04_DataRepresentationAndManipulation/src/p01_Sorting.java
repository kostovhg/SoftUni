import java.util.Arrays;
import java.util.Scanner;

public class p01_Sorting {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int[] nums = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        //long startTime = System.currentTimeMillis();
        //BubbleSort(nums);
        SelectionSort(nums);
        //long stopTime = System.currentTimeMillis();
        //System.out.println(stopTime - startTime);
        for (int num :
                nums) {
            System.out.print(num + " ");
        }

    }

    public static void BubbleSort(int[] arr){
        boolean swap = true;

        while (swap) {
            swap = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i]) {
                    int tmp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = tmp;
                    swap = true;
                }
            }
        }
    }

    public static void SelectionSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            // search from the next part of the array
            // for lowest value
            for (int curr = i + 1; curr < arr.length; curr++) {
                if(arr[curr] < arr[min]){
                    min = curr;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
    }


}