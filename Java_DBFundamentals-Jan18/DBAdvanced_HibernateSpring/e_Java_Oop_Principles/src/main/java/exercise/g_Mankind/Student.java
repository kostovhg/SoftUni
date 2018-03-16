package exercise.g_Mankind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student extends Human{

    public static final String INVALID_FACULTY_NUMBER = "Invalid faculty number!";
    private String facultyNumber;

    public Student(String[] args) {
        super(args[0], args[1]);
        this.setFacultyNumber(args[2].trim());
    }

    private void setFacultyNumber(String facultyNumber) {
        if(!validFacNumber(facultyNumber)){
            throw new IllegalArgumentException(INVALID_FACULTY_NUMBER);
        }
        this.facultyNumber = facultyNumber;
    }

    public String getFacultyNumber() {
        return this.facultyNumber;
    }

    private boolean validFacNumber(String value){
        String numPattern = "^([a-zA-Z0-9]{5,10})$";
        Pattern p = Pattern.compile(numPattern);
        Matcher m = p.matcher(value);
        if (m.find()){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("Faculty number: ")
                .append(getFacultyNumber())
                .append(System.lineSeparator());

        return sb.toString();
    }
}
