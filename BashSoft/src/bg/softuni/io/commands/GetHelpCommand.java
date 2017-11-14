package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.AsynchDownloader;
import bg.softuni.contracts.Database;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.OutputWriter;

public class GetHelpCommand extends Command implements Executable {

    public GetHelpCommand(String input,
                          String[] data,
                          ContentComparer tester,
                          Database repository,
                          AsynchDownloader downloadManager,
                          DirectoryManager inputOutputManager) {
        super(input, data, repository, tester, inputOutputManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {

        if (this.getData().length != 1) {
            throw new InvalidCommandException(this.getInput());
        }

        StringBuilder helpBuilder = new StringBuilder();
        helpBuilder.append("make directory - mkdir nameOfFolder")
                .append(System.lineSeparator());
        helpBuilder.append("traverse directory - ls depth")
                .append(System.lineSeparator());
        helpBuilder.append("comparing files - cmp absolutePath1 absolutePath2")
                .append(System.lineSeparator());
        helpBuilder.append("change directory - cdRel relativePath or \"..\" for level up")
                .append(System.lineSeparator());
        helpBuilder.append("change directory - cdAbs absolutePath")
                .append(System.lineSeparator());
        helpBuilder.append("read students data base - readDb fileName")
                .append(System.lineSeparator());
        helpBuilder.append("filter students - filter {courseName} excellent/average/poor take 2/5/all")
                .append(System.lineSeparator());
        helpBuilder.append("order students - order {courseName} ascending/descending take 20/10/all")
                .append(System.lineSeparator());
        helpBuilder.append("download file - download URL (saved in current directory)")
                .append(System.lineSeparator());
        helpBuilder.append("download file on new thread - downloadAsynch URL (saved under current directory in folder downloads)")
                .append(System.lineSeparator());
        helpBuilder.append("drop currently loaded DB – dropdb")
                .append(System.lineSeparator());
        helpBuilder.append("get help – help")
                .append(System.lineSeparator());
        OutputWriter.writeMessage(helpBuilder.toString());
    }
}
