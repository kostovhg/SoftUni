package lab.g_FindAndSumIntegers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Optional<Integer> sum = Arrays.stream(
                reader.readLine().split("\\s+"))
                .filter(x -> isNumber(x))
                .map(Integer::valueOf)
                .reduce((x1, x2) -> x1 + x2);
        //      .reduce(Integer::sum);

        if (sum.isPresent()) {
            System.out.println(sum.get());
        } else {
            System.out.println("No match");
        }
    }

    private static boolean isNumber(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        } catch(NullPointerException npe) {
            return false;
        }/* finally {
            System.out.println("Some error");
        }*/
    }
}