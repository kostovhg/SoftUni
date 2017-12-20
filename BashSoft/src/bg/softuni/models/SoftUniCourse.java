package bg.softuni.models;

import bg.softuni.contracts.Course;
import bg.softuni.contracts.Student;
import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.exceptions.InvalidStringException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SoftUniCourse implements Course {

    private String name;
    private Map<String, Student> studentsByName;

    public SoftUniCourse(String name) {
        this.name = name;
        this.studentsByName = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String courseName) {
        if(courseName == null || courseName.equals("")){
            throw new InvalidStringException();
        }
        this.name = courseName;
    }

    @Override
    public Map<String, Student> getStudentsByName() {
        return Collections.unmodifiableMap(this.studentsByName);
    }

    @Override
    public void enrollStudent(Student student) {
        if(this.studentsByName.containsKey(student.getUserName())) {
            throw new DuplicateEntryInStructureException(student.getUserName(), this.name);
        }

        this.studentsByName.put(student.getUserName(), student);
    }

    @Override
    public int compareTo(Course o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
