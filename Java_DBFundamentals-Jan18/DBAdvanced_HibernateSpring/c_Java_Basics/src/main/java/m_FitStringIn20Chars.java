import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class m_FitStringIn20Chars {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        StringBuilder output = new StringBuilder();
        if(input.length() > 20){
            output.append(input.substring(0, 20));
        } else {
            output.append(input).append(repeat("*", 20 - input.length()));
        }
        System.out.println(output);
    }

    private static String repeat(String string, int times) {
        StringBuilder out = new StringBuilder();
        while (times-- > 0) {
            out.append(string);
        }
        return out.toString();
    }
}
