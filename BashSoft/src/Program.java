import IO.InputReader;

import java.io.IOException;

public class Program {
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
        InputReader.readCommand();
    }
}
