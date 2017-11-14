package bg.softuni.io.commands;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;

public class ChangeRelativePathCommand extends Command {
    public ChangeRelativePathCommand(String input,
                                     String[] data,
                                     Tester tester,
                                     StudentsRepository repository,
                                     DownloadManager downloadManager,
                                     IOManager inputOutputManager) {
        super(input, data, repository, tester, inputOutputManager, downloadManager);
    }


    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String relativePath = this.getData()[1];
        this.getIoManager().changeCurrentDirRelativePath(relativePath);
    }
}
