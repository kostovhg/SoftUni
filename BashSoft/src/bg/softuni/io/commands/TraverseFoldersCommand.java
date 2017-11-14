package bg.softuni.io.commands;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;

public class TraverseFoldersCommand extends Command {
    public TraverseFoldersCommand(String input, 
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
