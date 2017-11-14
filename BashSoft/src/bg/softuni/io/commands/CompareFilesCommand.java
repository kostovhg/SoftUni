package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.Database;
import bg.softuni.contracts.AsynchDownloader;;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.exceptions.InvalidPathException;

import java.io.IOException;

public class CompareFilesCommand extends Command implements Executable {

    public CompareFilesCommand(String input,
                               String[] data,
                               ContentComparer tester,
                               Database repository,
                               AsynchDownloader downloadManager,
                               DirectoryManager ioManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {

        String[] data = this.getData();

        if (data.length != 3) {
            throw new InvalidCommandException(this.getInput());
        }

        String firstPath = data[1];
        String secondPath = data[2];
        try {
            this.getTester().compareContent(firstPath, secondPath);
        } catch (IOException ioe) {
            throw new InvalidPathException();
        }
    }
}
