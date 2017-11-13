package bg.softuni.models;

import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.exceptions.InvalidStringException;
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
            throw new InvalidStringException();
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

    public void enrollStudent(Student student) throws DuplicateEntryInStructureException {
        if(this.studentsByName.containsKey(student.getUserName())) {
            throw new DuplicateEntryInStructureException(student.getUserName(), this.name);
        }

        this.studentsByName.put(student.getUserName(), student);
    }
}
