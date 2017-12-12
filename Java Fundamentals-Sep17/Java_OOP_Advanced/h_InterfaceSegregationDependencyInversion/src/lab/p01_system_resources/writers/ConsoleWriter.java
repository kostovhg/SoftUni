package lab.p01_system_resources.writers;

import lab.p01_system_resources.contracts.Writer;

public class ConsoleWriter  implements Writer{
    @Override
    public void write(String msg) {
        System.out.println(msg);
    }
}
