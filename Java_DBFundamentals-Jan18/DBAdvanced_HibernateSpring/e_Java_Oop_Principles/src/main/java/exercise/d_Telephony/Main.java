package exercise.d_Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Smartphone thePhone = new Smartphone("000000000");

        List<String> numbers = new ArrayList<>();
        List<String> URLs = new ArrayList<>();

        numbers.addAll(Arrays.stream(reader.readLine().split("\\s+")).collect(Collectors.toList()));
        URLs.addAll(Arrays.stream(reader.readLine().split("\\s+")).collect(Collectors.toList()));

        for (String number : numbers) {
            System.out.println(thePhone.call(number));
        }

        for (String url : URLs) {
            System.out.println(thePhone.browse(url));
        }
    }
}