import java.util.Scanner;

public class p01_CalculateTriangleAreaMethod {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double base = input.nextDouble();
        double height = Double.parseDouble(input.nextLine());

        System.out.printf("Area = %.2f", calcArea(base, height));

    }

    private static double calcArea(double base, double height) {
        return (base * height) / 2.0;
    }
}