package bg.softuni.io.commands;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.IOManager;

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

        if (this.getData().length != 5) {
            throw new InvalidCommandException(this.getInput());
        }

        String courseName = this.getData()[1];
        String orderType = this.getData()[2].toLowerCase();
        String takeCommand = this.getData()[3].toLowerCase();
        String takeQuantity = this.getData()[4].toLowerCase();

        tryParseParametersForOrder(takeCommand, takeQuantity, courseName, orderType);
    }

    private void tryParseParametersForOrder(
            String takeCommand, String takeQuantity,
            String courseName, String orderType) {
        if (!takeCommand.equals("take")) {
            throw new InvalidInputException(INVALID_TAKE_COMMAND);
        }

        if (takeQuantity.equals("all")) {
            this.getRepository().orderAndTake(courseName, orderType);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.getRepository().orderAndTake(courseName, orderType, studentsToTake);
        } catch (NumberFormatException nfe) {
            throw new InvalidInputException(INVALID_TAKE_QUANTITY_PARAMETER);
        }
    }
}
