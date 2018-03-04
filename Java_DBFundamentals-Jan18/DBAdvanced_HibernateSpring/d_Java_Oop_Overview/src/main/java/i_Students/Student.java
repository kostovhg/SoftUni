package i_Students;

public class Student {

    private String name;

    private static int studentsCount = 0;

    public Student(String name) {
        this.name = name;
        studentsCount++;
    }

    public static int getStudentsCount() {
        return studentsCount;
    }

}
