package d_NumberInReversedOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        DecimalNumber num = new DecimalNumber(reader.readLine());
        System.out.println(num.reverseDigits());
    }
}
