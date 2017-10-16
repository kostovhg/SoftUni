import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class p09_SerializeCustomObject {
    static class Cube implements Serializable {
        String color;
        Double width;
        Double height;
        Double depth;
    }
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("files/output09.ser");
        Cube cube = new Cube();
        cube.color = "green";
        cube.width = 15.3d;
        cube.height = 12.4d;
        cube.depth = 3d;

        FileOutputStream fos = new FileOutputStream(String.valueOf(path));
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(cube);
    }
}
