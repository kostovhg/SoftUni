import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class k_EqualSums {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get the array from the input
        String[] input = br.readLine().split("\\s+");
        int[] arr = new int[input.length];
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
            rightSum += arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            rightSum -= arr[i];
            if (leftSum == rightSum){
                System.out.println(i);
                return;
            } else {
                leftSum += arr[i];
            }
        }

        System.out.println("no");
    }
}
