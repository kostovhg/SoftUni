package demo.commands;

import demo.annotations.Command;
import demo.interfaces.Executable;

@Command(value = "Exit")
public class C implements Executable {
    @Override
    public void execute() {
        System.exit(0);
    }
}
