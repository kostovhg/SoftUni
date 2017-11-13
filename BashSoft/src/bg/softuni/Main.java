package bg.softuni;

import bg.softuni.Judge.Tester;
import bg.softuni.Network.DownloadManager;
import bg.softuni.Repository.*;
import bg.softuni.io.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        StudentRepository.initializeData();
        // Currently the data imports comes from the console
        // where user should paste the data.txt content
        // and the query is hardcoded to search students in course "Unity"
        StudentRepository.getStudentsByCourse("Unity");
        */

        /*
        String path = System.getProperty("user.dir") + "\\inputFiles\\";
        String test1path = path + "test2.txt";
        String test2path = path + "test3.txt";
        Tester.compareContent(test1path, test2path);
        */

        /*
        IOManager.createDirectoryInCurrentFolder("pesho");
        */

        /*
        IOManager.changeCurrentDirAbsolute("C:\\Users");
        IOManager.traverseDirectory(3);
        */

        Tester tester = new Tester();
        DownloadManager downloadManager = new DownloadManager();
        IOManager ioManager = new IOManager();
        RepositorySorters sorter = new RepositorySorters();
        RepositoryFilters filter = new RepositoryFilters();
        StudentsRepository repository = new StudentsRepository(filter, sorter);
        CommandInterpreter currentInterpreter =
                new CommandInterpreter(tester, repository, downloadManager, ioManager);
        InputReader reader = new InputReader(currentInterpreter);

        try{
            reader.readCommands();
        } catch (Exception e){
            OutputWriter.displayException(e.getMessage());
        }

    }
}
