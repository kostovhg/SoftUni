import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class l_ReverseString {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String input = reader.readLine();

        for (int i = input.length() - 1; i > -1; i--) {
            System.out.print(input.charAt(i));
        }
    }
}
