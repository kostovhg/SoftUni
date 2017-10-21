package Repository;

import IO.OutputWriter;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorters {

    public static void printSortedStudents(
            HashMap<String, ArrayList<Integer>> courseData,
            String comparisonType,
            int numberOfStudents) {

        /* Comparator<Map.Entry<String, ArrayList<Integer>>> comparator = createComparator(comparisonType); */
        Comparator<Map.Entry<String, ArrayList<Integer>>> comparator = (x, y) ->
                Double.compare(
                        x.getValue().stream().mapToInt(Integer::valueOf).average().getAsDouble(),
                        y.getValue().stream().mapToInt(Integer::valueOf).average().getAsDouble());
        /* shorter option will be
         Comparator.comparingDouble(x -> x.getValue().stream().mapToInt(Integer::valueOf).average().getAsDouble());
         */


        /*  old variant
        ArrayList<Map.Entry<String, ArrayList<Integer>>> sortedStudents = new ArrayList<>();
        sortedStudents.addAll(courseData.entrySet());

        Collections.sort(sortedStudents, comparator);
        */

        List<String> sortedStudents = courseData.entrySet()
                .stream()
                .sorted(comparator)
                .limit(numberOfStudents)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (comparisonType.equals("descending")) {
            Collections.reverse(sortedStudents);
        }

        for(String student : sortedStudents){
            OutputWriter.printStudent(student, courseData.get(student));
        }
    }

    /* Methods replaced with lambda
    private static Comparator<Map.Entry<String, ArrayList<Integer>>> createComparator(String comparisonType) {
        switch(comparisonType){
            case "ascending":
                return new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
                    // can be replaced with lambda
                    @Override
                    public int compare(
                            Map.Entry<String, ArrayList<Integer>> firstStudent,
                            Map.Entry<String, ArrayList<Integer>> secondStudent){

                        Double firstStudentTotal = getTotalScore(firstStudent.getValue());
                        Double secondStudentTotal = getTotalScore(secondStudent.getValue());

                        return firstStudentTotal.compareTo(secondStudentTotal);
                    }
                };
            case "descending":
                return new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
                    @Override
                    public int compare(
                    Map.Entry<String, ArrayList<Integer>> firstStudent,
                    Map.Entry<String, ArrayList<Integer>> secondStudent) {

                Double firstStudentTotal = getTotalScore(firstStudent.getValue());
                Double secondStudentTotal = getTotalScore(secondStudent.getValue());

                return secondStudentTotal.compareTo(firstStudentTotal);
                    }
                };
            default: return null;
        }
    }

    private static Double getTotalScore(ArrayList<Integer> grades) {
        Double total = 0d;
        total = (double)grades.stream().mapToInt(x -> x).sum();
        return total / grades.size();
    }*/
}
