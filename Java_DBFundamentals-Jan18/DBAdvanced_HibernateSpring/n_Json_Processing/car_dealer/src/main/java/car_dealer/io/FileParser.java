package car_dealer.io;

import org.springframework.stereotype.Component;

import java.io.*;

import static car_dealer.utils.Constants.JSON_OUTPUT_FILES_DIRECTORY;
import static car_dealer.utils.Constants.RESOURCES_DIRECTORY;
import static car_dealer.utils.Constants.SYSTEM_DIRECTORY;

@Component
public class FileParser {

    public String readFile(String fileName) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (
                InputStream is = getClass().getResourceAsStream(fileName);
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is))
        ) {
            String line = null;
            while ((line = bfr.readLine()) != null) {
                fileContent.append(line);
            }
        }

        return fileContent.toString();
    }

    public void writeFile(String fileName, String content) throws IOException {
        String path = SYSTEM_DIRECTORY + RESOURCES_DIRECTORY + JSON_OUTPUT_FILES_DIRECTORY + fileName;
        File f = new File(path);
        if(!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        try (
        FileWriter writer = new FileWriter(f)){
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (OutputStream os = new FileOutputStream(f);
//             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
//        ) {
//            bfw.write(String.valueOf(content));
//        }
    }

}
