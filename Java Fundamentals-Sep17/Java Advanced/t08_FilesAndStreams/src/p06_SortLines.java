import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class p06_SortLines {
    public static void main(String[] args) throws IOException {
        Path inPath = Paths.get("files/input.txt");
        Path outPath = Paths.get("files/output06.txt");

        List<String> lines = Files.readAllLines(inPath);
        Collections.sort(lines);
        Files.write(outPath, lines);
    }
}
