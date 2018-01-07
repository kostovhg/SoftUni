package theExpanse.io;

public class ConsoleOutputWriter {

    public void printLine(String line){
        System.out.println(line);
    }

    public void printLine(String format, String... params) {
        System.out.println(String.format(format, params));
    }
}
