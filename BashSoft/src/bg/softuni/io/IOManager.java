package bg.softuni.io;

import bg.softuni.StaticData.SessionData;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.exceptions.InvalidFileNameException;
import bg.softuni.exceptions.InvalidPathException;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class IOManager implements DirectoryManager {
    /*
    Breadth First Search algorithm for traversing all folders
    in a given path
     */
    public void traverseDirectory(int depth) {
        Queue<File> subFolders = new LinkedList<>();

        String path = SessionData.currentPath;
        // todo: fix the paths !!! currently project starts in BashSoft\src\
        int initialIndentation = path.split("\\\\").length;
        File root = new File(path);

        /* Queue the folder at the start of the queue */
        subFolders.add(root);

        while (subFolders.size() != 0) {
            /* Add all its subfolders to the end of the queue */
            File currentFolder = subFolders.poll();

            /* Calculating the depth we are reaching with each loop */
            int currentIndentation = currentFolder.toString().split("\\\\").length - initialIndentation;

            /* stop this iteration if we reach the depth */
            if (depth - currentIndentation < 0) {
                break;
            }
            OutputWriter.writeMessageOnNewLine(currentFolder.toString());
            /* if there is content in the folder */
            if (currentFolder.listFiles() != null) {
                try {
                    // And we have access to its content
                    for (File file : currentFolder.listFiles()) {
                        // Enqueue all children folders
                        if (file.isDirectory()) {
                            subFolders.add(file);
                        } else {
                            /* if we have file, replace path with "-" and print filename */
                            int indexOfLastSlash = file.toString().lastIndexOf("\\");
                            for (int i = 0; i < indexOfLastSlash; i++) {
                                OutputWriter.writeMessage("-");
                            }
                            OutputWriter.writeMessageOnNewLine(file.getName());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Access denied");
                }
            }
            /* Print the current folder */
        }
    }

    public void createDirectoryInCurrentFolder(String name) throws InvalidFileNameException {
        String path = SessionData.currentPath + "\\" + name;
        File file = new File(path);
        boolean wasDirMade = file.mkdir();
        if (!wasDirMade) {
            throw new InvalidFileNameException();
        }
    }

    public void changeCurrentDirRelativePath(String relativePath) throws InvalidPathException {
        if (relativePath.equals("..")) {
            /* go one directory up */
            try {
                String currentPath = SessionData.currentPath;
                int indexOfLastSlash = currentPath.indexOf("\\");
                String newPath = currentPath.substring(0, indexOfLastSlash);
                SessionData.currentPath = newPath;
            } catch (Exception sioobe) {
                throw new InvalidPathException();
            }
        } else {
            /* go to a given directory */
            String currentPath = SessionData.currentPath;
            currentPath += "\\" + relativePath;
            changeCurrentDirAbsolute(currentPath);
        }
    }

    public void changeCurrentDirAbsolute(String absolutePath) throws InvalidPathException {
        File file = new File(absolutePath);
        if (!file.exists()) {
            throw new InvalidPathException();
        }
        SessionData.currentPath = absolutePath;
    }
}
