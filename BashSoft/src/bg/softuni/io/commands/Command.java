package bg.softuni.io.commands;

import bg.softuni.contracts.ContentComparer;
import bg.softuni.contracts.Database;
import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

public abstract class Command implements Executable {

    private String input;
    private String[] data;
    private Database repository;
    private ContentComparer tester;
    private DirectoryManager ioManager;
    private AsynchDownloader downloadManager;

    public Command(String input,
                   String[] data,
                   Database repository,
                   ContentComparer tester,
                   DirectoryManager ioManager,
                   AsynchDownloader downloadManager) {
        this.setInput(input);
        this.setData(data);
        this.repository = repository;
        this.tester = tester;
        this.ioManager = ioManager;
        this.downloadManager = downloadManager;
    }

    protected String getInput() {
        return input;
    }

    private void setInput(String input) {
        if (input == null || input.equals("")) {
            throw new InvalidCommandException(input);
        }
        this.input = input;
    }

    protected String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidCommandException();
        }
        this.data = data;
    }

    protected Database getRepository() {
        return repository;
    }

    protected ContentComparer getTester() {
        return tester;
    }

    protected DirectoryManager getIoManager() {
        return ioManager;
    }

    protected AsynchDownloader getDownloadManager() {
        return downloadManager;
    }

    public abstract void execute() throws Exception;
}
