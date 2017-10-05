import java.util.Arrays;
import java.util.Scanner;

public class p07_FindTheMissingNumber {
    static int[] nums;
    static int len;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        len = Integer.parseInt(scan.nextLine());
        nums = Arrays.stream(scan.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();
        mergeSort(nums);
        if (nums[len - 2] < len) {
            System.out.println(len);
            return;
        }
        for (int i = 1; i < len; i++) {
            if(nums[i - 1] != i){
                System.out.println(i);
                return;
            }
        }
    }

    private static void mergeSort(int[] nums) {
        if(nums.length <= 1) return;
        // pre action: split current array
        int[] a = new int[nums.length / 2];
        int[] b = new int[nums.length - a.length];

        for (int i = 0; i < nums.length; i++) {
            if(i < a.length) a[i] = nums[i];
            else b[i - a.length] = nums[i];
        }

        // recursively sort result arrays
        mergeSort(a);
        mergeSort(b);

        // post action: merge current sorted arrays
        // to previously split array
        int ai = 0;
        int bi = 0;
        while(ai + bi < nums.length){
            if(bi >= b.length ||
                    (ai < a.length && a[ai] < b[bi])){
                nums[ai + bi] = a[ai];
                ai++;
            } else {
                nums[ai + bi] = b[bi];
                bi++;
            }
        }

    }

}