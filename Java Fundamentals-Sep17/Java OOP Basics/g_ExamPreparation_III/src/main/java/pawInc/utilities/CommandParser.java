package pawInc.utilities;

import pawInc.contracts.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pawInc.utilities.Constants.SPLITTER_PATTERN;

public class CommandParser implements Parser {

    public CommandParser() {
    }

    @Override
    public List<String> parseInput(String inputLine) {
        return new ArrayList<>(Arrays.asList(inputLine.split(SPLITTER_PATTERN)));
    }
}
