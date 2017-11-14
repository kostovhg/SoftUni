package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.AsynchDownloader;
import bg.softuni.contracts.Database;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidCommandException;

public class MakeDirectoryCommand extends Command implements Executable {

    public MakeDirectoryCommand(String input,
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
        if (data.length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String folderName = data[1];
        this.getIoManager().createDirectoryInCurrentFolder(folderName);
    }
}
