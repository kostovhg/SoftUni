import java.io.*;

public class p05_WriteEveryThirdLine {
    public static void main(String[] args) {
        String inputPath = "files/input.txt";
        String outputPath = "files/output05.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            PrintWriter writer = new PrintWriter(outputPath)) {
            String line = reader.readLine();
            int count = 1;
            while(line != null){
                if(count % 3 == 0){
                    writer.println(line);
                }
                line = reader.readLine();
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
