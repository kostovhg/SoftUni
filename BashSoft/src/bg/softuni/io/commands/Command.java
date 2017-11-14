package bg.softuni.io.commands;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.StudentsRepository;
import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;

public abstract class Command {

    private String input;
    private String[] data;
    private StudentsRepository repository;
    private Tester tester;
    private IOManager ioManager;
    private DownloadManager downloadManager;

    public Command(String input, String[] data, StudentsRepository repository, Tester tester, IOManager ioManager, DownloadManager downloadManager) {
        this.setInput(input);
        this.setData(data);
        this.repository = repository;
        this.tester = tester;
        this.ioManager = ioManager;
        this.downloadManager = downloadManager;
    }

    String getInput() {
        return input;
    }

    protected String[] getData() {
        return data;
    }

    private void setInput(String input) {
        if(input == null || input.equals("")){
            throw new InvalidCommandException(input);
        }
        this.input = input;
    }

    public void setData(String[] data) {
        if(data == null || data.length < 1){
            throw new InvalidCommandException();
        }
        this.data = data;
    }

    protected StudentsRepository getRepository() {
        return repository;
    }

    Tester getTester() {
        return tester;
    }

    IOManager getIoManager() {
        return ioManager;
    }

    protected DownloadManager getDownloadManager() {
        return downloadManager;
    }

    public abstract void execute() throws Exception;
}
