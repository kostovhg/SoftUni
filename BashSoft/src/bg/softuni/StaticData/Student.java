package bg.softuni.StaticData;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student {

    private String userName;
    private LinkedHashMap<String, Course> enrolledCourses;
    private LinkedHashMap<String, Double> marksByCourseName;

    public Student(String userName) {
        this.setUserName(userName);
        this.enrolledCourses = new LinkedHashMap<>();
        this.marksByCourseName = new LinkedHashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_OR_EMPTY_VALUE);
        }
        this.userName = userName;
    }

    public Map<String, Course> getEnrolledCourses() {
        return Collections.unmodifiableMap(this.enrolledCourses);
    }

    public void setEnrolledCourses(LinkedHashMap<String, Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(this.marksByCourseName);
    }

    public void setMarksByCourseName(LinkedHashMap<String, Double> marksByCourseName) {
        this.marksByCourseName = marksByCourseName;
    }
}
