package lab.p01_system_resources;

import lab.p01_system_resources.contracts.TimeProvider;
import lab.p01_system_resources.contracts.Writer;

public class GreetingClock {

    private TimeProvider time;
    private Writer writer;

    public GreetingClock(TimeProvider time) {
        this.time = time;
    }

    public GreetingClock(TimeProvider time, Writer writer) {
        this.time = time;
        this.writer = writer;
    }

    public void setTimeProvider(TimeProvider time) {
        this.time = time;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void greeting() {
        if (time.getHour() < 12) {
            writer.write("Good morning...");
        } else if (time.getHour() < 18) {
            writer.write("Good afternoon...");
        } else {
            writer.write("Good evening...");
        }
    }
}
