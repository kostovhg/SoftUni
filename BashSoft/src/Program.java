public class Program {
    public static void main(String[] args) {
        StudentRepository.initializeData();
        // Currently the data imports comes from the console
        // where user should paste the data.txt content
        // and the query is hardcoded to search students in course "Unity"
        StudentRepository.getStudentsByCourse("Unity");
    }
}
