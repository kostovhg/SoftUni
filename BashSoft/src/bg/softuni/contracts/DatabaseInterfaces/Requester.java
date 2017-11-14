package bg.softuni.contracts.DatabaseInterfaces;

public interface Requester {

    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);
}
