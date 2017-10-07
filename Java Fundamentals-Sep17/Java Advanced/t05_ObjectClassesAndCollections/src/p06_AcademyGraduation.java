import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.DoubleStream;

public class p06_AcademyGraduation {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        HashMap<String, Double> students = new HashMap<>();
        String line;
        int count = Integer.parseInt(scann.nextLine());
        for (int i = 0; i < count; i++) {
            String name = scann.nextLine();
            double[] grades =  Arrays.stream(scann.nextLine().split("\\s+"))
                    .mapToDouble(Double::parseDouble).toArray();
            Double aver = 0.0;
            for (double grade :
                    grades) {
                aver += grade;
            }
            students.put(name, aver / grades.length);
        }

        for (String name :
                students.keySet()) {
            System.out.printf("%s is graduated with ", name);
            System.out.println(students.get(name));
        }
    }
}