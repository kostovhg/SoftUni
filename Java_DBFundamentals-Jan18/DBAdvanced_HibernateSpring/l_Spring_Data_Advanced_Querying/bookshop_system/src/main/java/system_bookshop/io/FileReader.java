package system_bookshop.io;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileReader implements Reader {

    @Override
    public List<String> read(String file) throws IOException {

        List<String> lines = new ArrayList<>();

        try (final BufferedReader bufferedReader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(file), "UTF8"))
        ) {
            bufferedReader.mark(4);
            if('\ufeff' != bufferedReader.read()){
                bufferedReader.reset();
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }

}
