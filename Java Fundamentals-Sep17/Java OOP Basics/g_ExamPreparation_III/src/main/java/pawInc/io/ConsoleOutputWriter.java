package pawInc.io;

import pawInc.contracts.Writer;

public class ConsoleOutputWriter implements Writer {

    @Override
    public void printLine(String line){
        System.out.println(line);
    }
}
