import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class n_CensorEmailAddress {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String mailToBeHidden = reader.readLine();
        String[] usernameAndDomain = mailToBeHidden.split("@");
        String mask = String.join("", Collections.nCopies(usernameAndDomain[0].length(), "*")) +
                "@" +
                usernameAndDomain[1];

        String textToModify = reader.readLine();

        System.out.println(textToModify.replaceAll(mailToBeHidden, mask));

        
    }
}
