package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.AsynchDownloader;
import bg.softuni.contracts.Database;
import bg.softuni.contracts.AsynchDownloader;;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidCommandException;

public class TraverseFoldersCommand extends Command implements Executable {
    public TraverseFoldersCommand(String input,
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
        if (data.length != 1 && data.length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        if (data.length == 1) {
            this.getIoManager().traverseDirectory(0);
            return;
        }

        if (data.length == 2) {
            this.getIoManager().traverseDirectory(Integer.valueOf(data[1]));
        }
    }
}
