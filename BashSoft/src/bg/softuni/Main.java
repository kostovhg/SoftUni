package bg.softuni;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.*;
import bg.softuni.contracts.*;
import bg.softuni.io.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ContentComparer tester = new Tester();
        AsynchDownloader downloadManager = new DownloadManager();
        DirectoryManager ioManager = new IOManager();
        DataSorter sorter = new RepositorySorters();
        DataFilter filter = new RepositoryFilters();
        Database repository = new StudentsRepository(filter, sorter);
        Interpreter currentInterpreter =
                new CommandInterpreter(tester, repository, downloadManager, ioManager);
        Reader reader = new InputReader(currentInterpreter);

        try{
            reader.readCommands();
        } catch (Exception e){
            OutputWriter.displayException(e.getMessage());
        }

    }
}
