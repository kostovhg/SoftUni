import java.io.*;
import java.util.Scanner;

public class p04_ExtractIntegers {

    public static void main(String[] args) {
        String input = "files/input.txt";
        String output = "files/output04.txt";
        try(Scanner reader = new Scanner(new FileInputStream(input));
        PrintWriter writer = new PrintWriter(output)){
            while(reader.hasNext()){
                if(reader.hasNextInt()){
                    writer.println(reader.nextInt());
                }
                reader.next();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}