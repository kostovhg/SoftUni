import java.util.Scanner;

public class p01_StudentsResults {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] input = sc.nextLine().split("[ ,-]+");
        double[] grades = new double[input.length];
        double sum = 0;
        for (int i = 0; i < grades.length; i++) {
            if(i < grades.length - 1){
                grades[i] = Double.parseDouble(input[i + 1]);
                sum += grades[i];
            } else {
                grades[i] = sum / i;
            }

        }

        System.out.printf("%1$-10s|%2$7s|%3$7s|%4$7s|%5$7s|%n",
                "Name", "JAdv", "JavaOOP", "AdvOOP", "Average");
        System.out.printf("%1$-10s|%2$7.2f|%3$7.2f|%4$7.2f|%5$7.4f|%n",
                input[0], grades[0], grades[1], grades[2], grades[3]);
    }
}