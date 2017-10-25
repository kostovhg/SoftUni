import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p01_BitSnow {
    // Modified reader method!!!!! not faster than 122 ms.
    public static void main(String[] args) throws IOException {
        /*
        * start from bottom. take all 1 down if we have 0
        * count if we have some move, if don't break cycle
        * go from bottom to the second row
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputString = br.readLine().split(", ");
        int[] nums = new int[inputString.length];
        for (int i = 0; i < inputString.length; i++) {
            nums[i] = Integer.parseInt(inputString[i]);
        }

        for (int i = nums.length -1; i > 0; i--) {
            for (int above = i - 1; above > -1; above--) {
                int temp = nums[i];
                nums[i] |= nums[above];
                nums[above] &= temp;
            }
        }

        StringBuilder output = new StringBuilder();
        for (int num : nums) {
            output.append(num).append(", ");
        }
        output.setLength(output.length() - 2);
        System.out.println(output);
        /*String output = Arrays.toString(nums);
        System.out.println(output.substring(1, output.length() -1));*/
    }

}
