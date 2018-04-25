package org.softuni.mostwanted.io.impl;

import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO {
    @Override
    public String read(String file) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (
                InputStream is = getClass().getResourceAsStream(file);
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is, "UTF-8"))
        ) {
            String line = null;
            while ((line = bfr.readLine()) != null) {
                fileContent.append(line);
            }
        }

        return fileContent.toString();
    }

    @Override
    public void write(String fileContent, String file) throws IOException {
        String path = file;
        File f = new File(path);
        if(!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        try (OutputStream os = new FileOutputStream(file);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
        ) {
            bfw.write(String.valueOf(fileContent));
        }
    }
}
