package p03_Mankind;

public class Student extends Human{

    private String getFacultyNumber() {
        return facultyNumber;
    }

    private String facultyNumber;

    public Student(String fName, String lName, String fNum) {
        super(fName, lName);
        this.setFacultyNumber(fNum);
    }

    private void setFacultyNumber(String fNum) throws IllegalArgumentException {
        if (fNum.length() < 5 || fNum.length() > 10) {
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        this.facultyNumber = fNum;
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
