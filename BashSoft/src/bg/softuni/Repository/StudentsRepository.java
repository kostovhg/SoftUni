package bg.softuni.Repository;

import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.StaticData.SessionData;
import bg.softuni.models.Student;
import bg.softuni.io.OutputWriter;
import bg.softuni.models.Course;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentsRepository {
    /* boolean variable to check if data is already initialized */
    private boolean isDataInitialized = false;
    /* Map that will contains courses with students with list of their grades */
    //private HashMap<String, HashMap<String, ArrayList<Integer>>> studentsByCourse;
    private LinkedHashMap<String, Course> courses;
    private LinkedHashMap<String, Student> students;
    private RepositoryFilters filter;
    private RepositorySorters sorter;

    public StudentsRepository(RepositoryFilters filter, RepositorySorters sorter) {
        this.filter = filter;
        this.sorter = sorter;
    }

    public void loadData(String fileName) throws IOException {
        if (this.isDataInitialized) {
            throw new RuntimeException (ExceptionMessages.DATA_ALREADY_INITIALIZED);
        }
        /* Initialize the Map */
        this.students = new LinkedHashMap<>();
        this.courses = new LinkedHashMap<>();
        this.readData(fileName);
    }

    public void unloadData() {
        if (!this.isDataInitialized) {
            throw new RuntimeException(ExceptionMessages.DATA_NOT_INITIALIZED);
        }

        this.students = null;
        this.courses = null;
        this.isDataInitialized = false;
    }

    private void readData(String fileName) throws IOException {
        /* Method to fill the Map from file */
        String regex = "([A-Z][a-zA-Z#\\+]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Za-z]+\\d{2}_\\d{2,4})\\s([\\s0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        //String path = SessionData.currentPath + "\\" + fileName;
        String path = SessionData.currentPath + "\\inputFiles\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = lines.get(lineIndex);
            matcher = pattern.matcher(line);

            if (!line.isEmpty() && matcher.find()) {

                String courseName = matcher.group(1);
                String studentName = matcher.group(2);
                String scoresStr = matcher.group(3);

                try{
                    String[] splitScores = scoresStr.split("\\s+");
                    int[] scores = new int[splitScores.length];
                    for (int i = 0; i < splitScores.length; i++) {
                        scores[i] = Integer.parseInt(splitScores[i]);
                    }

                    if(Arrays.stream(scores).anyMatch(score -> score > 100 || score < 0)) {
                        OutputWriter.displayException(ExceptionMessages.INVALID_SCORE);
                        continue;
                    }

                    if(scores.length > Course.NUMBER_OF_TASKS_ON_EXAM) {
                        OutputWriter.displayException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
                        continue;
                    }

                    if (!this.students.containsKey(studentName)){
                        this.students.put(studentName, new Student(studentName));
                    }
                    if(!this.courses.containsKey(courseName)) {
                        this.courses.put(courseName, new Course(courseName));
                    }

                    Course course = this.courses.get(courseName);
                    Student student = this.students.get(studentName);
                    student.enrollInCourse(course);
                    student.setMarksOnCourse(courseName, scores);
                    course.enrollStudent(student);
                } catch (NumberFormatException nfe) {
                    OutputWriter.displayException(
                            nfe.getMessage() + " at line: " + lineIndex);
                }
            }
        }
        isDataInitialized = true;
        OutputWriter.writeMessageOnNewLine("Data read.");
    }

    private boolean isQueryForCoursePossible(String courseName) {
        if (!this.isDataInitialized) {
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }

        if (!this.courses.containsKey(courseName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
            return false;
        }

        return true;
    }

    private boolean isQueryForStudentPossible(String courseName, String studentName) {
        if (!isQueryForCoursePossible(courseName)) {
            return false;
        }

        if (!this.courses.get(courseName).getStudentsByName().containsKey(studentName)) {
            throw new NullPointerException(ExceptionMessages.NON_EXISTING_STUDENT);
        }
        return true;
    }

    /* Get student and print its marks */
    public void getStudentMarkInCourse(String courseName, String studentName) {
        if (!isQueryForStudentPossible(courseName, studentName)) {
            return;
        }

        double mark = this.courses.get(courseName).getStudentsByName()
                .get(studentName).getMarksByCourseName().get(courseName);
        OutputWriter.printStudent(studentName, mark);
    }

    public void getStudentsByCourse(String courseName) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        OutputWriter.writeMessageOnNewLine(courseName + ":");
        for (Map.Entry<String, Student> student : this.courses.get(courseName).getStudentsByName().entrySet()) {
            this.getStudentMarkInCourse(courseName, student.getKey());
        }
    }

    public void filterAndTake(String courseName, String filter) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }
        int studentsToTake = this.courses.get(courseName).getStudentsByName().size();
        filterAndTake(courseName, filter, studentsToTake);

    }

    public void filterAndTake(String courseName, String filterType, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        LinkedHashMap<String, Double> marks = new LinkedHashMap<>();
        for(Map.Entry<String, Student> entry : this.courses.get(courseName).getStudentsByName().entrySet()){
            marks.put(entry.getKey(), entry.getValue().getMarksByCourseName().get(courseName));
        }
        this.filter.printFilteredStudents(marks, filterType, studentsToTake);
    }

    public void orderAndTake(String courseName, String orderType, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        LinkedHashMap<String, Double> marks = new LinkedHashMap<>();
        for(Map.Entry<String, Student> entry : this.courses.get(courseName).getStudentsByName().entrySet()){
            marks.put(entry.getKey(), entry.getValue().getMarksByCourseName().get(courseName));
        }
        this.sorter.printSortedStudents(marks, orderType, studentsToTake);
    }

    public void orderAndTake(String courseName, String orderType) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }
        int studentsToTake = this.courses.get(courseName).getStudentsByName().size();
        orderAndTake(courseName, orderType, studentsToTake);
    }
}

