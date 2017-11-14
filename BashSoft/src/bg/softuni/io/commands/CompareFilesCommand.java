package bg.softuni.io.commands;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.exceptions.InvalidPathException;
import bg.softuni.io.IOManager;

import java.io.IOException;

public class CompareFilesCommand extends Command {


    public CompareFilesCommand(String input,
                               String[] data,
                               Tester tester,
                               StudentsRepository repository,
                               DownloadManager downloadManager,
                               IOManager ioManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {

        if (this.getData().length != 3) {
            throw new InvalidCommandException(this.getInput());
        }

        String firstPath = this.getData()[1];
        String secondPath = this.getData()[2];
        try {
            this.getTester().compareContent(firstPath, secondPath);
        } catch (IOException ioe){
            throw new InvalidPathException();
        }
    }
}
