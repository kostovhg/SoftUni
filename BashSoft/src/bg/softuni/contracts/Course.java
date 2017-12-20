package bg.softuni.contracts;

import java.util.Map;

public interface Course extends Comparable<Course> {
    double NUMBER_OF_TASKS_ON_EXAM = 5;
    double MAX_SCORE_ON_EXAM_TASK = 100;
    String getName();

    Map<String, Student> getStudentsByName();

    void enrollStudent(Student student);
}
