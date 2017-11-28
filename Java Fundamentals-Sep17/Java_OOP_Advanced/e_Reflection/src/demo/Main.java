package demo;

import demo.annotations.Command;
import demo.interfaces.Executable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final String COMMANDS_PACKAGE = "demo.commands.";
    private static final String PATH = "src/demo/commands";

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        File[] commands = new File(PATH).listFiles();

        while(true){
            String command = reader.readLine();

            for (File file : commands) {
                Class c = Class.forName(COMMANDS_PACKAGE + file.getName().replace(".java", ""));
                Command  a = (Command) c.getAnnotation(Command.class);
                String value = a.value();
                Executable executable = (Executable) c.newInstance();
                if(value.equals(command)){
                    executable.execute();
                }
            }
        }
    }
}