import java.util.Arrays;
import java.util.Scanner;

public class p03_BinarySearch {
    private static Scanner sc = new Scanner(System.in);

    static int[] nums;
    static int key;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        nums = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        key = Integer.parseInt(scanner.nextLine());

        System.out.println(binarySearch( 0, nums.length));
    }

    private static int binarySearch(int lo, int hi){
        if(lo < hi){
            int mid = lo + (hi - lo) / 2;
            if(key < nums[mid]) {
                return binarySearch(lo, mid);
            } else if (key > nums[mid]) {
                return binarySearch(mid + 1 , hi);
            } else {
                return mid;
            }
        } else {
            return -1;
        }
    }
}