import http.HttpRequest;
import http.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Mixed simple solution
 */
public class Main {

     private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

         private String readLine() {
             String result = null;

             try {
                 result = reader.readLine();
             } catch (IOException e) {
                 e.printStackTrace();
             }

             return result;
         }

    public static void main(String[] args) throws IOException {
        HttpRequest request = getRequest();

        StringBuilder response = new StringBuilder();
        request.getCookies().forEach(c -> response
                .append(String.format("%s <-> %s", c.getKey(), c.getValue()))
                .append(System.lineSeparator()));

        System.out.println(response.toString());

    }

    public static HttpRequest getRequest() throws IOException {
        StringBuilder input = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null && !line.isEmpty()){
            input.append(line).append(System.lineSeparator());
        }

        input.append(System.lineSeparator());

        if((line = reader.readLine()) != null && !line.isEmpty()){
            input.append(line).append(System.lineSeparator());
        }

        return new HttpRequestImpl(input.toString());
    }
}
