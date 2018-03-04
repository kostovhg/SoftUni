package c_LastDigitName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        c_LastDigitName.Number num = new c_LastDigitName.Number(Integer.parseInt(reader.readLine()));
        System.out.println(num.getLastDigitName());

    }
}
