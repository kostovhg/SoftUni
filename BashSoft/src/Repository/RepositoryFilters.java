package Repository;

import StaticData.ExceptionMessages;

import IO.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class RepositoryFilters {

    public static void printFilteredStudents(
            HashMap<String, ArrayList<Integer>> courseData,
            String filterType,
            Integer numberOfStudents) {
        Predicate<Double> filter = createFilter(filterType);
        if (filter == null) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.INVALID_FILTER);
        }

        int studentsCount = 0;
        for (String student : courseData.keySet()){
            if(studentsCount >= numberOfStudents) break;

            ArrayList<Integer> studentMarks = courseData.get(student);
            Double averageMark =  studentMarks
                    .stream()
                    .mapToInt(Integer::valueOf)
                    .average()
                    .getAsDouble();
            Double percentageOfFulfilment = averageMark / 100 ;
            Double mark = percentageOfFulfilment * 4 + 2;
            if(filter.test(mark)) {
                OutputWriter.printStudent(student, studentMarks);
                studentsCount++;
            }
        }
    }

    private static Predicate<Double> createFilter(String filterType){
        switch (filterType){
            case "excellent":
                return mark -> mark >= 5.0;
            case "average":
                return mark -> 3.5 <= mark && mark < 5.0;
            case "poor":
                return mark -> mark < 3.5;
                default: return null;
        }
    }

   /* private static Double getStudentAverageGrade(ArrayList<Integer> grades){
        Double totalScore = 0d;
        totalScore = (double) grades.stream().mapToInt(x -> x).sum();
        Double percentage = totalScore / (grades.size() * 100);
        return (percentage * 4) + 2;
    }*/


}