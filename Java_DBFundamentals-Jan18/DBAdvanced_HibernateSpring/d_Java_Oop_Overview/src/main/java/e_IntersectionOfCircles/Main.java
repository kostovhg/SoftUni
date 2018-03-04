package e_IntersectionOfCircles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Circle firstCircle = new Circle(reader.readLine().split("\\s+"));
        Circle secondCircle = new Circle(reader.readLine().split("\\s+"));

        System.out.println(
                firstCircle.intersectWithCircle(secondCircle) ?
                        "Yes" :
                        "No");
    }
}