import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class p01_ReadFile {

    public static void main(String[] args) throws IOException {
        String path = "files/input.txt";
        FileInputStream fileStream = null;
        try{
            fileStream = new FileInputStream(path);
            int oneByte = fileStream.read();
            while (oneByte >= 0){
                System.out.printf("%s ", Integer.toBinaryString(oneByte));
                oneByte = fileStream.read();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try{
                if(fileStream != null) fileStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}