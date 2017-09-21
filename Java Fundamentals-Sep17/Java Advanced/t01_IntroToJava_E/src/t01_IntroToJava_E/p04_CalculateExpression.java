package t01_IntroToJava_E;

import java.util.Scanner;

public class p04_CalculateExpression {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Read three floating point numbers a, b and c and calculate
        F1 = ((a * a + b * b)/(a * a - b * b))^((a + b + c)/sqrt(c))
        F2 = (a * a + b * b - c ^ 3)^ (a - b)
        then the absolute value of the difference
        Abs(Avg(a, b, c) - Avg(F1, F2))
         */
        Double a = input.nextDouble();
        Double b = input.nextDouble();
        Double c = input.nextDouble();

        double resF1 = Math.pow((a * a + b * b)/(a * a - b * b), (a + b + c)/Math.sqrt(c));;
        double resF2 = Math.pow((a * a + b * b - Math.pow(c, 3)), (a - b));

        System.out.printf("F1 result: %.2f; F2 result: %.2f; Diff: %.2f", resF1, resF2, Math.abs((( a + b + c) / 3) - (resF1 + resF2) / 2));
    }
}