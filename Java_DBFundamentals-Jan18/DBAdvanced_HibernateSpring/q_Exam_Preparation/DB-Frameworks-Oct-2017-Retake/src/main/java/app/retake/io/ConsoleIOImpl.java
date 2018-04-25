package app.retake.io;

import app.retake.io.api.ConsoleIO;
import org.springframework.stereotype.Component;

@Component
public class ConsoleIOImpl implements ConsoleIO {

    @Override
    public void write(String line){
        System.out.println(line);
    }
}
