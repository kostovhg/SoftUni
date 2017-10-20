import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRepository {
    /* boolean variable to check if data is already initialized */
    public static boolean isDataInitialized = false;
    /* Map that will contains courses with students with list of their grades */
    public static HashMap<String, HashMap<String, ArrayList<Integer>>> studentsByCourse;

    public static void initializeData(String fileName) throws IOException {
        if(isDataInitialized) {
            System.out.println(ExceptionMessages.DATA_ALREADY_INITIALIZED);
            return;
        }
        /* Initialize the Map */
        studentsByCourse = new HashMap<>();
        readData(fileName);
    }

    private static void readData(String fileName) throws IOException {
        /* Method to fill the Map from file */
        String regex = "([A-Z][a-zA-Z#+]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Z][a-z]{0,3}\\d{2}_\\d{2,4})\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        String path = SessionData.currentPath + "\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));

        for(String line : lines){
            matcher = pattern.matcher(line);

            if(!line.isEmpty() && matcher.find()) {

                String course = matcher.group(1);
                String student = matcher.group(2);
                Integer mark = Integer.parseInt(matcher.group(3));

                if (mark >= 0 && mark <= 100) {
                    if (!studentsByCourse.containsKey(course)) {
                        studentsByCourse.put(course, new HashMap<>());
                    }
                    if (!studentsByCourse.get(course).containsKey(student)) {
                        studentsByCourse.get(course).put(student, new ArrayList<>());
                    }
                    studentsByCourse.get(course).get(student).add(mark);
                }
                isDataInitialized = true;
            }
        }
        OutputWriter.writeMessageOnNewLine("Data read.");
    }

    private static boolean isQueryForCoursePossible(String courseName){
        if(!isDataInitialized){
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }

        if(!studentsByCourse.containsKey(courseName)){
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
        }

        return true;
    }

    private static boolean isQueryForStudentPossible(String courseName, String studentName){
        if(!isQueryForCoursePossible(courseName)){
            return false;
        }

        if(!studentsByCourse.get(courseName).containsKey(studentName)){
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_STUDENT);
            return false;
        }
        return true;
    }

    /* Get student and print its marks */
    public static void getStudentMarksInCourse(String course, String student){
        if(!isQueryForStudentPossible(course, student)){
            return;
        }

        /* Collect grades of the student in variable */
        ArrayList<Integer> marks = studentsByCourse.get(course).get(student);
        OutputWriter.printStudent(student, marks);
    }

    public static void getStudentsByCourse(String course){
        if(!isQueryForCoursePossible(course)){
            return;
        }

        OutputWriter.writeMessageOnNewLine(course +":");
        for(Map.Entry<String, ArrayList<Integer>> student : studentsByCourse.get(course).entrySet()){
            OutputWriter.printStudent(student.getKey(), student.getValue());
        }
    }

    public static void printFilteredStudents(String course, String filter, Integer numberOfStudents){
        if (! isQueryForCoursePossible(course)){
            return;
        }
        if (numberOfStudents == null) {
            numberOfStudents = studentsByCourse.get(course).size();
        }

        RepositoryFilters.printFilteredStudents(studentsByCourse.get(course), filter, numberOfStudents);
    }

    public static void printOrderedStudents(String course, String compareType, Integer numberOfStudents) {
        if (! isQueryForCoursePossible(course)) return;
        if (numberOfStudents == null) {
            numberOfStudents = studentsByCourse.get(course).size();
        }

        RepositorySorters.printSortedStudents(studentsByCourse.get(course), compareType, numberOfStudents);
    }
}
