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

        for (int index = 0; index < 16; index++) {
            int n = (1 << index);
            // start by colums 0 till colum 15
            for (int row = nums.length - 1; row > 0 ;row--) {
                // start by bottom row, check the above one
                if ((nums[row] & n) > 0){
                    continue;
                } else {
                    int above = row - 1;
                    while (above > 0 && (nums[above] & n) == 0) {
                        above--;
                    }
                    if ((nums[above] & n) > 0) {
                        nums[row] = nums[row] | n;
                        nums[above] = nums[above] & ~n;
                        continue;
                    }
                }
            }

        }
        StringBuilder output = new StringBuilder();
        for (int num :
                nums) {
            output.append(num).append(", ");
        }
        output.setLength(output.length() - 2);
        System.out.println(output);
        /*String output = Arrays.toString(nums);
        System.out.println(output.substring(1, output.length() -1));*/
    }

}
