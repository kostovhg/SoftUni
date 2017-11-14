package bg.softuni.io.commands;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.IOManager;
import bg.softuni.io.OutputWriter;

import static bg.softuni.StaticData.ExceptionMessages.INVALID_TAKE_COMMAND;
import static bg.softuni.StaticData.ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER;

public class PrintOrderedStudentsCommand extends Command {
    public PrintOrderedStudentsCommand(String input,
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

        if (data.length != 5) {
            throw new InvalidCommandException(this.getInput());
        }

        String courseName = data[1];
        String orderType = data[2].toLowerCase();
        String takeCommand = data[3].toLowerCase();
        String takeQuantity = data[4].toLowerCase();

        tryParseParametersForOrder(takeCommand, takeQuantity, courseName, orderType);
    }

    private void tryParseParametersForOrder(
            String takeCommand, String takeQuantity,
            String courseName, String orderType) {
        if (!takeCommand.equals("take")) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_COMMAND);
        }

        if (takeQuantity.equals("all")) {
            this.getRepository().orderAndTake(courseName, orderType);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.getRepository().orderAndTake(courseName, orderType, studentsToTake);
        } catch (NumberFormatException nfe) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
        }
    }
}
