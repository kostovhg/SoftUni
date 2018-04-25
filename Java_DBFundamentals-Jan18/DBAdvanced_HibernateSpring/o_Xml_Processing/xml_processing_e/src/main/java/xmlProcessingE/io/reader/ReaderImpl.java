package xmlProcessingE.io.reader;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;

@Component
public class ReaderImpl implements Reader {

    @Override
    public String readAll(String fileName) {

        InputStream inputStream = Reader.class.getResourceAsStream(fileName);
        try {
            return StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
