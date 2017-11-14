package bg.softuni.contracts.DatabaseInterfaces;

public interface FilteredTaker {

    void filterAndTake(String courseName, String filterType, int studentsToTake);

    void filterAndTake(String courseName, String filterType);
}
