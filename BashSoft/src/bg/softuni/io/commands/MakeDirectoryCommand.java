package bg.softuni.io.commands;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;

public class MakeDirectoryCommand extends Command {

    public MakeDirectoryCommand(String input,
                                String[] data,
                                Tester tester,
                                StudentsRepository repository,
                                DownloadManager downloadManager,
                                IOManager ioManager) {
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
