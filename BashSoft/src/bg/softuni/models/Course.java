package bg.softuni.models;

import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.io.OutputWriter;

import java.util.LinkedHashMap;

public class Course {
    public static final double NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final double MAX_SCORE_ON_EXAM_TASK = 100;

    public String name;
    public LinkedHashMap<String, Student> studentsByName;

    public Course(String name) {
        this.name = name;
        this.studentsByName = new LinkedHashMap<>();
    }

    public void enrollStudent(Student student) {
        if(this.studentsByName.containsKey(student.userName)) {
            OutputWriter.displayException(String.format(
                    ExceptionMessages.STUDENT_ALREADY_ENROLLED_IN_GIVEN_COURSE,
                    student.userName, this.name));
            return;
        }

        this.studentsByName.put(student.userName, student);
    }
}
