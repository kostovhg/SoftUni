import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class p07_ListFiles {
    public static void main(String[] args) throws FileNotFoundException {
        File dir = new File("files/Files-and-Streams/");
        String outPath = "files/output07.txt";
        PrintWriter writer = new PrintWriter(outPath);

        if(dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if(!file.isDirectory()){
                    String info = String.format("%s: %s",
                            file.getName(), file.length());
                    writer.println(info);
                    System.out.println(info);
                }
            }
            writer.close();
        }


    }
}
