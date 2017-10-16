import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class p03_CopyBytes {

    public static void main(String[] args){
        String input = "files/input.txt";
        String output = "files/output03.txt";
        try (FileInputStream fsIn = new FileInputStream(input)){
            FileOutputStream fsOut = new FileOutputStream(output);
            int oneByte;
            while((oneByte = fsIn.read()) >= 0){
                if(oneByte == 10 || oneByte == 32){
                    fsOut.write(oneByte);
                } else {
                    char[] digits = String.valueOf(oneByte).toCharArray();
                    for (Character ch : digits) {
                        fsOut.write(ch);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}