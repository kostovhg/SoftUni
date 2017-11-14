package bg.softuni.contracts;

import java.util.Map;

public interface Student {
    String getUserName();

    Map<String, Course> getEnrolledCourses();

    Map<String, Double> getMarksByCourseName();

    void enrollInCourse(Course course);

    void setMarksOnCourse(String courseName, int... scores);

    String getMarksForCourse(String courseName);
}
