package g_AverageGrades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();
        int studentsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < studentsCount; i++) {
            students.add(new Student(reader.readLine().split("\\s+")));
        }

        StringBuilder result = new StringBuilder();

        for (Student student : students
                .stream()
                .filter(x -> x.getAverageGrade() >= 5.0)
                .sorted()
                .collect(Collectors.toList())) {
            result.append(student.toString()).append(System.lineSeparator());
        }

        System.out.println(result.toString().trim());

    }
}