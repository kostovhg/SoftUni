package cresla.io;

import cresla.interfaces.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {

    @Override
    public void write(String output) {
        System.out.print(output);
    }

    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }
}
