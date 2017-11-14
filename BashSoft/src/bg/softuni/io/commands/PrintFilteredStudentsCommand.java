package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.AsynchDownloader;
import bg.softuni.contracts.Database;
import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.OutputWriter;

public class PrintFilteredStudentsCommand extends Command implements Executable {
    public PrintFilteredStudentsCommand(String input,
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
        if (data.length != 5) {
            throw new InvalidCommandException(this.getInput());
        }

        String course = data[1];
        String filter = data[2].toLowerCase();
        String takeCommand = data[3].toLowerCase();
        String takeQuantity = data[4].toLowerCase();

        tryParseParametersForFilter(takeCommand, takeQuantity, course, filter);
    }

    private void tryParseParametersForFilter(
            String takeCommand, String takeQuantity,
            String courseName, String filter) {
        if (!takeCommand.equals("take")) {
            throw new InvalidInputException(ExceptionMessages.INVALID_TAKE_COMMAND);
        }

        if (takeQuantity.equals("all")) {
            this.getRepository().filterAndTake(courseName, filter);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.getRepository().filterAndTake(courseName, filter, studentsToTake);
        } catch (NumberFormatException nfe) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
        }
    }
}
