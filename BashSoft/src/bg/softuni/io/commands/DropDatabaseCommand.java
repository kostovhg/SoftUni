package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.AsynchDownloader;
import bg.softuni.contracts.Database;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.OutputWriter;

public class DropDatabaseCommand extends Command implements Executable {
    public DropDatabaseCommand(String input,
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
        if (data.length != 1) {
            throw new InvalidCommandException(this.getInput());
        }

        this.getRepository().unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }
}
