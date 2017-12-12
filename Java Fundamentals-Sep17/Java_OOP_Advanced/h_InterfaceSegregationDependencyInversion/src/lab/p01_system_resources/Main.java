package lab.p01_system_resources;

import lab.p01_system_resources.contracts.TimeProvider;
import lab.p01_system_resources.contracts.Writer;
import lab.p01_system_resources.hourProviders.TestTime;
import lab.p01_system_resources.writers.ConsoleWriter;

public class Main {
    public static void main(String[] args) {

        TimeProvider time = new TestTime();
        Writer writer = new ConsoleWriter();

        GreetingClock greetingClock = new GreetingClock(time, writer);

        greetingClock.greeting();
    }
}
