package bg.softuni.Repository;

import bg.softuni.contracts.DataSorter;
import bg.softuni.io.OutputWriter;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorters implements DataSorter{

    @Override
    public void printSortedStudents(
            HashMap<String, Double> courseData,
            String comparisonType,
            int numberOfStudents) {

        Comparator<Map.Entry<String, Double>> comparator = (x, y) -> {
            double value1 = x.getValue();
            double value2 = y.getValue();
            return Double.compare(value1, value2);
        };

        List<String> sortedStudents = courseData.entrySet()
                .stream()
                .sorted(comparator)
                .limit(numberOfStudents)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (comparisonType.equals("descending")) {
            Collections.reverse(sortedStudents);
        }

        this.printStudents(courseData, sortedStudents);
    }

    private void printStudents(HashMap<String, Double> courseData, List<String> sortedStudents) {
        for(String student : sortedStudents) {
            OutputWriter.printStudent(student, courseData.get(student));
        }
    }
}
