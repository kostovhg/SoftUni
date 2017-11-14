package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.AsynchDownloader;
import bg.softuni.contracts.Database;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidCommandException;

public class ShowCourseCommand extends Command implements Executable {
    public ShowCourseCommand(String input,
                             String[] data,
                             ContentComparer tester,
                             Database repository,
                             AsynchDownloader downloadManager,
                             DirectoryManager inputOutputManager) {
        super(input, data, repository, tester, inputOutputManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2 && data.length != 3) {
            throw new InvalidCommandException(this.getInput());
        }

        if (data.length == 2) {
            String courseName = data[1];
            this.getRepository().getStudentsByCourse(courseName);
            return; // ?
        }

        if (data.length == 3) {
            String courseName = data[1];
            String userName = data[2];
            this.getRepository().getStudentMarkInCourse(courseName, userName);
        }
    }
}
