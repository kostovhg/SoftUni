package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.AsynchDownloader;
import bg.softuni.contracts.Database;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidCommandException;

public class ChangeAbsolutePathCommand extends Command implements Executable {
    public ChangeAbsolutePathCommand(String input,
                                     String[] data,
                                     ContentComparer tester,
                                     Database repository,
                                     AsynchDownloader downloadManager,
                                     DirectoryManager inputOutputManager) {
        super(input, data, repository, tester, inputOutputManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String absolutePath = this.getData()[1];
        this.getIoManager().changeCurrentDirAbsolute(absolutePath);
    }
}
