package p05_RandomArrayLIst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        RandomArrayList cal = new RandomArrayList();
        cal.add("sdas");
        cal.add(3);
        cal.add("b");
        cal.add(4);

        System.out.println(cal.getRandomElement());
        System.out.println(cal);

    }
}