import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p02_ReadAFile {
    public static void main(String[] args) {
        List<Character> punctuation = new ArrayList<>(Arrays.asList(',', '.', '!', '?'));
        String pathIn = "files/input.txt";
        String pathOut = "files/out02.txt";

        try(FileInputStream fsIn = new FileInputStream(pathIn)){
            FileOutputStream fsOut = new FileOutputStream(pathOut);
            int oneByte;
            while((oneByte = fsIn.read()) >= 0){
                if(!punctuation.contains((char)oneByte)){
                    fsOut.write(oneByte);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}