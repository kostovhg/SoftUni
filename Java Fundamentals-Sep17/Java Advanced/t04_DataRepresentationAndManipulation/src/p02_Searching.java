import java.util.Arrays;
import java.util.Scanner;

public class p02_Searching {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] nums = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int toFind = Integer.parseInt(scan.nextLine());

        System.out.println(BinarySearch(nums, toFind));
        //System.out.println(LeanerSearch(nums, toFind));


    }

    private static int LeanerSearch(int[] nums, int toFind) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == toFind){
                return i;
            }
        }
        return -1;
    }

    private static int BinarySearch(int[] nums, int toFind) {
        int low = 0;
        int hight = nums.length - 1;

        while(hight >= low){
            int middle = (low + hight) / 2;
            if(nums[middle] == toFind){
                return middle;
            }
            if(nums[middle] < toFind){
                low = middle + 1;
            }
            if(nums[middle] > toFind){
                hight = middle - 1;
            }
        }
        return -1;
    }
}