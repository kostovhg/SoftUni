package SystemSplit.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {

    public InputParser() {}

    public List<String> parseInput(String inputLine) {
        ArrayList<String> commands = new ArrayList<>();
        commands.add(inputLine.substring(0, inputLine.indexOf("(")));
        commands.addAll(Arrays.asList(inputLine.substring(
                inputLine.indexOf("(") + 1,
                inputLine.length() - 1)
                .split(", ")));

        return commands;
    }
}
