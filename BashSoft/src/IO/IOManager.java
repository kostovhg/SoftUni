package IO;

import StaticData.SessionData;
import StaticData.ExceptionMessages;

import java.io.File;
import java.util.LinkedList;

public class IOManager {
    /*
    Breadth First Search algorithum for traversing all folders
    in a given path
     */
    public static void traverseDirectory(int depth) {
        LinkedList<File> subFolders = new LinkedList<>();
        String path = SessionData.currentPath;
        int initialIdentation = path.split("\\\\").length;
        File root = new File(path);

        /* Queue the folder at the start of the queue */
        subFolders.add(root);
        while(subFolders.size() != 0){
            /* Add all its subfolders to the end of the queue */
            File currentFolder = subFolders.removeFirst();

            /* Calculating the depth we are reaching with each loop */
            int currentIdentation = currentFolder.toString().split("\\\\").length - initialIdentation;

            /* stop this iteration if we reach the depth */
            if(depth - currentIdentation < 0){
                break;
            }
            System.out.println(currentFolder.toString());
            /* if there is content in the folder */
            if(currentFolder.listFiles() != null){
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
                } catch (Exception e){
                    System.out.println("Access denied");
                }
            }
            /* Print the current folder */

        }
    }

    public static void createDirectoryInCurrentFolder(String name){
        String path = getCurrentDirectoryPath() + "\\" + name;
        File file = new File(path);
        file.mkdir();
    }

    public static String getCurrentDirectoryPath(){
        String currentPath = SessionData.currentPath;
        return currentPath;
    }

    public static void changeCurrentDirRelativePath(String relativePath){
        if(relativePath.equals("..")){
            /* go one directory up */
            String currentPath = SessionData.currentPath;
            int indexOfLastSlash = currentPath.indexOf("\\");
            String newPath = currentPath.substring(0, indexOfLastSlash);
            SessionData.currentPath = newPath;
        } else {
            /* go to a given directory */
            String currentPath = SessionData.currentPath;
            currentPath += "\\" + relativePath;
            changeCurrentDirAbsolute(currentPath);
        }
    }

    public static void changeCurrentDirAbsolute(String absolutePath) {
        File file = new File(absolutePath);
        if(!file.exists()){
            OutputWriter.displayException(ExceptionMessages.INVALID_PATH);
            return;
        }
        SessionData.currentPath = absolutePath;

    }
}
