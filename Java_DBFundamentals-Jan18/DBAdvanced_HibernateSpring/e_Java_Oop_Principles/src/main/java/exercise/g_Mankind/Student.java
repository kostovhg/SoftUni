package exercise.g_Mankind;

import static exercise.g_Mankind.Constants.FNUM_PATTERN;
import static exercise.g_Mankind.Constants.STUDENT_TO_STRING_FORMAT;
import static exercise.g_Mankind.Constants.INVALID_FACULTY_NUMBER;

public class Student extends Human {

    private String facultyNumber;

    public Student(String[] args) {
        super(args[0], args[1]);
        this.setFacultyNumber(args[2].trim());
    }

    private void setFacultyNumber(String facultyNumber) {
        if(!FNUM_PATTERN.matcher(facultyNumber).find()){
            throw new IllegalArgumentException(
                    INVALID_FACULTY_NUMBER
            );
        }
        this.facultyNumber = facultyNumber;
    }

    @Override
    public String toString(){
        return String.format(
                STUDENT_TO_STRING_FORMAT,
                super.toString(),
                this.facultyNumber
        );
    }
}
