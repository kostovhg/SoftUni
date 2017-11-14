package bg.softuni.contracts;

import java.io.IOException;

public interface ContentComparer {
    void compareContent(String actualOutput, String expectedOutput) throws IOException;
}
