package p04_MordorsCrueltyPlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] input = reader.readLine().toLowerCase().split("\\s+");
        Wizard grey = new Wizard();

        for (String food : input ) {
            grey.eat(food);
        }

        System.out.println(grey.getPoints());
        System.out.println(grey.getMood());
    }
}