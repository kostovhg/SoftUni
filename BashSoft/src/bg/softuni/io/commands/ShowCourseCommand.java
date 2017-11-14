package bg.softuni.io.commands;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;

public class ShowCourseCommand extends Command {
    public ShowCourseCommand(String input,
                             String[] data,
                             Tester tester,
                             StudentsRepository repository,
                             DownloadManager downloadManager,
                             IOManager inputOutputManager) {
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
