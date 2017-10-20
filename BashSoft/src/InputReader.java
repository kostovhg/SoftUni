import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class InputReader {

    private static final String END_COMMAND = "quit";

    public static void readCommand() throws IOException {

        OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        while(!END_COMMAND.equals(input)){
            interpretCommand(input);
            OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

            input = sc.nextLine().trim();
        }
    }

    public static void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String command = data[0];
        switch (command){
            case "open":
                tryOpenFile(input, data);
                break;
            case "mkdir":
                tryCreateDirectory(input, data);
                break;
            case "ls":
                tryTraverseFolders(input, data);
                break;
            case "cmp":
                tryCompareFiles(input, data);
                break;
            case "changeDirRel":
                tryChangeRelativePath(input, data);
                break;
            case "changeDirAbs":
                tryChangeAbsolutePath(input, data);
                break;
            case "readDb":
                tryReadDatabaseFromFile(input, data);
                break;
            case "filter":
                tryPrintFilteredStudents(input, data);
                break;
            case "order":
                tryPrintOrderedStudents(input, data);
                break;
            case "download":
                // TODO: Implement method
                break;
            case "downloadAsync":
                // TODO: Implement method
                break;
            case "show":
                tryShowWantedCourse(input, data);
                break;
            case "help":
                giveHelp(input, data);
                break;
            default:
                displayInvalidCommandMessage(input);
                break;

        }
    }

    private static void tryPrintOrderedStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
            return;
        }
        String course = data[1];
        String order = data[2];
        if (data.length == 3) {
            StudentRepository.printOrderedStudents(course, order, null);
            return;
        }
        Integer numberOfStudents = Integer.valueOf(data[3]);
        if(data.length == 4) {
            StudentRepository.printOrderedStudents(course, order, numberOfStudents);
        }
    }

    private static void tryPrintFilteredStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
            return;
        }
         String course = data[1];
        String filter = data[2];

        if (data.length == 3) {
            StudentRepository.printFilteredStudents(course, filter, null);
            return;
        }
        Integer numberOfStudents = Integer.valueOf(data[3]);
        if (data.length == 4) {
            StudentRepository.printFilteredStudents(course, filter, numberOfStudents);
        }
    }

    private static void tryShowWantedCourse(String input, String[] data) {
        if (data.length != 2 && data.length != 3){
            displayInvalidCommandMessage(input);
            return;
        }

        if (data.length == 2) {
            String courseName = data[1];
            StudentRepository.getStudentsByCourse(courseName);
        }
        if (data.length == 3) {
            String courseName = data[1];
            String userName = data[2];
            StudentRepository.getStudentMarksInCourse(courseName, userName);
        }
    }

    private static void giveHelp(String input, String[] data) {
        // TODO: make if better with showing different help and check if content of data is valid
        // for now, only prints file.
        if (data.length != 1){
            System.out.println("parameters after command 'help' are not used");
        }
        OutputWriter.writeMessageOnNewLine("mkdir path - make directory");
        OutputWriter.writeMessageOnNewLine("ls depth - traverse directory");
        OutputWriter.writeMessageOnNewLine("cmp path1 path2 - compare two files");
        OutputWriter.writeMessageOnNewLine("changeDirRel relativePath - change directory");
        OutputWriter.writeMessageOnNewLine("changeDir absolutePath - change directory");
        OutputWriter.writeMessageOnNewLine("readDb path - read students data base");
        OutputWriter.writeMessageOnNewLine("filterExcelent - filter excelent students (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("filterExcelent path - filter excelent students (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("filterAverage - filter average students (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("filterAverage path - filter average students (the output is written in a file)");
        OutputWriter.writeMessageOnNewLine("filterPoor - filter low grade students (the output is on the console)");
        OutputWriter.writeMessageOnNewLine("filterPoor path - filter low grade students (the output is written in a file)");
        OutputWriter.writeMessageOnNewLine("order - sort students in increasing order (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("order path - sort students in increasing order (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("decOrder - sort students in decreasing order (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("decOrder path - sort students in decreasing order (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("download pathOfFile - download file (saved in current directory)");
        OutputWriter.writeMessageOnNewLine("downloadAsync path - download file asynchronously (save in the current directory)");
        OutputWriter.writeMessageOnNewLine("help - get help");
        OutputWriter.writeEmptyLine();
    }

    private static void tryReadDatabaseFromFile(String input, String[] data) throws IOException {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        String fileName = data[1];
        StudentRepository.initializeData(fileName);
    }

    private static void tryChangeAbsolutePath(String input, String[] data) {
        if (data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }
        String relativePath = data[1];
        IOManager.changeCurrentDirAbsolute(relativePath);
    }

    private static void tryChangeRelativePath(String input, String[] data) {
        if(data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }
        String relativePath = data[1];
        IOManager.changeCurrentDirRelativePath(relativePath);
    }

    private static void tryCompareFiles(String input, String[] data) {
        if(data.length != 3){
            displayInvalidCommandMessage(input);
            return;
        }
        String firstPath = data[1];
        String secondPath = data[2];
        Tester.compareContent(firstPath, secondPath);
    }

    private static void tryTraverseFolders(String input, String[] data) {
        if (data.length < 1 || data.length > 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        if (data.length == 1) {
            IOManager.traverseDirectory(0);
        }
        if (data.length == 2){
           IOManager.traverseDirectory(Integer.valueOf(data[1]));
        }
    }

    private static void tryCreateDirectory(String input, String[] data) {
        if(data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }
        String folderName = data[1];
        IOManager.createDirectoryInCurrentFolder(folderName);
    }

    private static void tryOpenFile(String input, String[] data) {
        if(data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }
        String fileName = data[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayInvalidCommandMessage(String input){
        String output = String.format("The command '%s' is invalid", input);
        OutputWriter.writeMessageOnNewLine(output);
    }
}
