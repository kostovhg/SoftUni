import java.io.File;
import java.util.LinkedList;

public class IOManager {
    /*
    Breadth First Search algorithum for traversing all folders
    in a given path
     */
    public static void traverseDirectory(String path) {
        LinkedList<File> subFolders = new LinkedList<>();
        File root = new File(path);

        /* Queue the folder at the start of the queue */
        subFolders.add(root);
        while(subFolders.size() != 0){
            /* Add all its subfolders to the end of the queue */
            File currentFolder = subFolders.poll();

            /* if there is content in the folder */
            if(currentFolder.listFiles() != null){
                try {
                    // And we have access to its content
                    for (File file : currentFolder.listFiles()) {
                        // Enqueue all children folders
                        if (file.isDirectory()) {
                            subFolders.add(file);
                        }
                    }
                } catch (Exception e){
                    System.out.println("Access denied");
                }
            }
            /* Print the current folder */
            System.out.println(currentFolder.toString());
        }
    }
}
