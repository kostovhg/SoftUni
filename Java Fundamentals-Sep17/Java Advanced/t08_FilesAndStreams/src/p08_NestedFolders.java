import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class p08_NestedFolders {
    public static void main(String[] args) throws IOException {
        final String stringPath = "files/Files-and-Streams" ;
        File file = new File(stringPath);
        PrintWriter writer = new PrintWriter("files/output08.txt");

        ArrayDeque<File> queue = new ArrayDeque<>();

        queue.offer(file);
        int counter = 1;
        while (!queue.isEmpty()){
            File currentDir = queue.poll();

            File[] nestedFiles = currentDir.listFiles();
            for (File nestedFile : nestedFiles) {
                if(nestedFile.isDirectory()){
                    queue.offer(nestedFile);
                    counter++;
                }
            }
            System.out.println(currentDir.getName());
            writer.println(currentDir.getName());
        }
        System.out.println(counter + " folders");
        writer.println(counter + " folders");
        writer.close();
    }
}
