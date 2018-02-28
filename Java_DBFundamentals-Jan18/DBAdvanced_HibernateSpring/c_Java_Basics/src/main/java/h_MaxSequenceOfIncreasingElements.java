import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class h_MaxSequenceOfIncreasingElements {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get the array from the input
        String[] input = br.readLine().split("\\s+");
        int[] arr = new int[input.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        // Set initial values for calculating variables
        int start = 0;
        int len = 1;
        int bestLength = len;
        int bestStart = start;

        for (int pos = 0; pos < arr.length - 1; pos++) {
            if (arr[pos] < arr[pos + 1]) {
                len++;
                if (len > bestLength) {
                    bestLength = len;
                    bestStart = start;
                }
            } else {
                start = pos + 1;
                len = 1;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = bestStart; i < bestStart + bestLength; i++) {
            sb.append(arr[i]);
            if (i + 1 < bestStart + bestLength) {
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }
}

