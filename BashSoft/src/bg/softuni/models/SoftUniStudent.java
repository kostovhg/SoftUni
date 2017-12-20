package bg.softuni.models;

import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.contracts.Course;
import bg.softuni.contracts.Student;
import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.exceptions.InvalidStringException;
import bg.softuni.exceptions.KeyNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SoftUniStudent implements Student {
    private String userName;
    private LinkedHashMap<String, Course> enrolledCourses;
    private LinkedHashMap<String, Double> marksByCourseName;

    public SoftUniStudent(String userName) {
        this.setUserName(userName);
        this.enrolledCourses = new LinkedHashMap<>();
        this.marksByCourseName = new LinkedHashMap<>();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        if(userName == null || userName.equals("")) {
            throw new InvalidStringException();
        }
        this.userName = userName;
    }

    @Override
    public Map<String, Course> getEnrolledCourses() {
        return Collections.unmodifiableMap(this.enrolledCourses);
    }

    @Override
    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(this.marksByCourseName);
    }

    @Override
    public void enrollInCourse (Course course) throws DuplicateEntryInStructureException {
        if(this.enrolledCourses.containsKey(course.getName())){
            throw new DuplicateEntryInStructureException(this.getUserName(), course.getName());
        }
        this.enrolledCourses.put(course.getName(), course);
    }

    @Override
    public void setMarksOnCourse(String courseName, int... scores) {
        if(!this.enrolledCourses.containsKey(courseName)){
            throw new KeyNotFoundException(this.enrolledCourses.get(courseName));
        }

        if(scores.length > Course.NUMBER_OF_TASKS_ON_EXAM){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
        }

        double mark = calculateMark(scores);
        this.marksByCourseName.put(courseName, mark);
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores).sum() /
                (double) (Course.NUMBER_OF_TASKS_ON_EXAM * Course.MAX_SCORE_ON_EXAM_TASK);
        return percentageOfSolvedExam * 4 + 2;
    }

    @Override
    public String getMarksForCourse(String courseName) {
        String output = String.format("%s - %f",
                this.userName, marksByCourseName.get(courseName));
        return output;
    }

    @Override
    public int compareTo(Student o) {
        return this.getUserName().compareTo(o.getUserName());
    }

    @Override
    public String toString(){
        return this.getUserName();
    }
}
