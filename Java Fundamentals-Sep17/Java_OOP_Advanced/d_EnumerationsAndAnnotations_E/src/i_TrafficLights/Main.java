package i_TrafficLights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split("\\s+");
        Signal[] trafficLights = new Signal[input.length];

        for (int i = 0; i < input.length; i++) {
            trafficLights[i] = Signal.valueOf(input[i]);
        }

        int cicles = Integer.parseInt(reader.readLine());

        for (int i = 0; i < cicles; i++) {
            for (int j = 0; j < trafficLights.length; j++) {
                trafficLights[j] = trafficLights[j].switchNext();
                System.out.print(trafficLights[j] + " ");
            }
            System.out.println();
        }
    }
}