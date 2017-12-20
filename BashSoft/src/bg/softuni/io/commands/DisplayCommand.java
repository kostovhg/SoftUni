package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.dataStructures.SimpleSortedList;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.OutputWriter;

import java.util.Comparator;

public class DisplayCommand extends Command implements Executable {

    public DisplayCommand(String input,
                          String[] data,
                          ContentComparer tester,
                          Database repository,
                          AsynchDownloader downloadManager,
                          DirectoryManager ioManager){
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if(data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        String entityToDisplay = data[1];
        String sortType = data[2];
        if(entityToDisplay.equalsIgnoreCase("students")) {
            Comparator<Student> studentComparator =
                    this.createStudentComparator(sortType);
            SimpleSortedList<Student> list = this.getRepository().getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(
                    list.joinWith(System.lineSeparator()));
        } else if (entityToDisplay.equalsIgnoreCase("courses")) {
            Comparator<Course> courseComparator =
                    this.createCourseComparator(sortType);
            SimpleSortedList<Course> list = this.getRepository().getAllCoursesSorted(courseComparator);
            OutputWriter.writeMessageOnNewLine(
                    list.joinWith(System.lineSeparator()));
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }

    private Comparator<Student> createStudentComparator (String sortType) {
        if(sortType.equalsIgnoreCase("ascending")) {
            return (o1, o2) -> o1.compareTo(o2);
        } else if (sortType.equalsIgnoreCase("descending")) {
            return (o1, o2) -> o2.compareTo(o1);
        } else {
            throw  new InvalidInputException(this.getInput());
        }
    }

    private Comparator<Course> createCourseComparator (String sortType) {
        if(sortType.equalsIgnoreCase("descending")) {
            return Comparator.reverseOrder();
        } else if (sortType.equalsIgnoreCase("ascending")) {
            return Comparator.naturalOrder();
        } else {
            throw  new InvalidInputException(this.getInput());
        }
    }
}
