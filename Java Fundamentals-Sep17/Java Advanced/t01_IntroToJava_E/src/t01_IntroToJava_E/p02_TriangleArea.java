package t01_IntroToJava_E;

import java.util.Scanner;

public class p02_TriangleArea {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Read 3 points (int x and y) on separate lines.
        Return the area of the triangle defined by those points
        Result is rounded to whole number.
        If there is no triangle returns 0
        */

        // take the input
        int xA = input.nextInt();
        int yA = input.nextInt();
        int xB = input.nextInt();
        int yB = input.nextInt();
        int xC = input.nextInt();
        int yC = input.nextInt();

        // formula taken from http://www.mathopenref.com/coordtrianglearea.html
        double result = Math.abs(xA * (yB - yC) + xB * (yC - yA) + xC * (yA - yB)) / 2;

        System.out.println(Math.round(result));
    }
}