package bg.softuni.models;

import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.io.OutputWriter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Course {
    public static final double NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final double MAX_SCORE_ON_EXAM_TASK = 100;

    private String name;
    private LinkedHashMap<String, Student> studentsByName;

    public String getName() {
        return name;
    }

    public void setName(String courseName) {
        if(courseName == null || courseName.equals("")){
            throw new IllegalArgumentException(
                    ExceptionMessages.NULL_OR_EMPTY_VALUE);
        }
        this.name = courseName;
    }

    public Map<String, Student> getStudentsByName() {
        return Collections.unmodifiableMap(this.studentsByName);
    }

    public Course(String name) {
        this.name = name;
        this.studentsByName = new LinkedHashMap<>();
    }

    public void enrollStudent(Student student) throws IllegalArgumentException {
        if(this.studentsByName.containsKey(student.getUserName())) {
            throw new IllegalArgumentException(String.format(
                    ExceptionMessages.STUDENT_ALREADY_ENROLLED_IN_GIVEN_COURSE,
                    student.getUserName(), this.name));
        }

        this.studentsByName.put(student.getUserName(), student);
    }
}
