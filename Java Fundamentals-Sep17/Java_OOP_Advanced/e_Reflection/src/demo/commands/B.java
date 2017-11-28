package demo.commands;

import demo.annotations.Command;
import demo.interfaces.Executable;

@Command(value = "b")
public class B  implements Executable{
    @Override
    public void execute(){
        System.out.println("Pesho");
    }
}
