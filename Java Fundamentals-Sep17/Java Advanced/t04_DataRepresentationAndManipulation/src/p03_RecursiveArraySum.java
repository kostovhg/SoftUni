import java.util.Arrays;
import java.util.Scanner;

public class p03_RecursiveArraySum {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] nums = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        long sum = RecArrSum(nums, 0);
        System.out.println(sum);
    }

    private static long RecArrSum(int[] nums, int i) {

        if(i == nums.length - 1) return nums[i];

        return nums[i] + RecArrSum(nums, i + 1);
    }
}