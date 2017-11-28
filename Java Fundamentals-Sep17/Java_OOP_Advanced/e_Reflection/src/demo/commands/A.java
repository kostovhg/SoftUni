package demo.commands;

import demo.annotations.Command;
import demo.interfaces.Executable;

@Command(value = "a")
public class A implements Executable{
    @Override
    public void execute(){
        System.out.println("Gosho");
    }
}
