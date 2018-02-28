import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class j_IndexOfLetters {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] alphabet = new char['z' - 'a' + 1];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char)('a' + i);
        }
        String alp = String.valueOf(alphabet);

        String input = reader.readLine();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int index = alp.indexOf(c);
            System.out.printf("%s -> %d", c, index);
            System.out.println();
        }
    }
}
