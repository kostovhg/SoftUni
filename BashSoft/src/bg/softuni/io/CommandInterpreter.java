package bg.softuni.io;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.commands.*;

import java.io.IOException;

public class CommandInterpreter {
    private Tester tester;
    private StudentsRepository repository;
    private DownloadManager downloadManager;
    private IOManager inputOutputManager;

    public CommandInterpreter(Tester tester,
                              StudentsRepository repository,
                              DownloadManager downloadManager,
                              IOManager inputOutputManager) {
        this.tester = tester;
        this.repository = repository;
        this.downloadManager = downloadManager;
        this.inputOutputManager = inputOutputManager;
    }

    void interpretCommand(String input) throws IOException{
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();
        try {
           Command command = parseCommand(input, data, commandName);
           command.execute();
        } catch (Throwable t) {
            OutputWriter.displayException(t.getMessage());
        }
    }

    private Command parseCommand(String input, String[] data, String command) throws Exception{

        switch (command) {
            case "open":
                return new OpenFileCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "mkdir":
                return new MakeDirectoryCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "ls":
                return new TraverseFoldersCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "cmp":
                return new CompareFilesCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "cdrel":
                return new ChangeRelativePathCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "cdabs":
                return new ChangeAbsolutePathCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "readdb":
                return new ReadDatabaseCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "help":
                return new GetHelpCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "show":
                return new ShowCourseCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "filter":
                return new PrintFilteredStudentsCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "order":
                return new PrintOrderedStudentsCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "download":
                return new DownloadFileCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "downloadasynch":
                return new DownloadAsyncCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            case "dropdb":
                return new DropDatabaseCommand(input, data, this.tester,
                        this.repository, this.downloadManager, this.inputOutputManager);
            default:
                throw new InvalidInputException(input);
        }
    }
}
