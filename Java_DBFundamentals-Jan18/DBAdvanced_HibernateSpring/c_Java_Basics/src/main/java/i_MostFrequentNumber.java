import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class i_MostFrequentNumber {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get the array from the input
        String[] input = br.readLine().split("\\s+");
        int[] arr = new int[input.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        Map<Integer, Integer> repetitions = new LinkedHashMap<>();

        for (int pos = 0; pos < arr.length; pos++) {
                if (repetitions.containsKey(arr[pos])){
                    repetitions.put(arr[pos], repetitions.get(arr[pos]) + 1);
                } else {
                    repetitions.put(arr[pos], 1);
            }
        }

        int maxCount = 0;
        int elementWithMaximumCounts = 0;
        for (Integer element : repetitions.keySet()) {
            if(repetitions.get(element) > maxCount){
                maxCount = repetitions.get(element);
                elementWithMaximumCounts = element;
            }
        }

        System.out.println(elementWithMaximumCounts);

    }
}
