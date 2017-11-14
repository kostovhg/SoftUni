package bg.softuni.contracts;

import java.util.HashMap;

public interface DataSorter {

    void printSortedStudents(
            HashMap<String, Double> courseData,
            String comparisonType,
            int numberOfStudents);
}
