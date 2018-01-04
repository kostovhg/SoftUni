package pawInc.engines;

import pawInc.contracts.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static pawInc.utilities.Constants.COMMAND_INDEX;
import static pawInc.utilities.Constants.TERMINATING_COMMAND;

public final class PawIncEngine implements Engine {

    private Reader reader;
    private Parser inputParser;
    private Manager manager;
    private Handler handler;

    public PawIncEngine(Reader reader, Parser inputParser, Handler handler, Manager manager) {
        this.reader = reader;
        this.inputParser = inputParser;
        this.handler = handler;
        this.manager = manager;
    }

    @Override
    public void run() throws IOException, InvocationTargetException, ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        String inputLine;

        while (true) {
            inputLine = this.reader.readLine();
            if (inputLine.equals(TERMINATING_COMMAND)) {
                this.manager.printStatistics();
                break;
            }
            List<String> commandParams = this.inputParser.parseInput(inputLine);
            String command = commandParams.remove(COMMAND_INDEX);
            command = Character.toLowerCase(command.charAt(0)) + command.substring(1);
            String result = this.handler.executeCommand(command, commandParams);
        }
    }

    // not used with reflection version (PawIncCommandHandler)
    private String dispatchCommand(List<String> comParams) {
        String com = comParams.remove(COMMAND_INDEX);

        switch (com) {
            case "RegisterCleansingCenter":
                this.manager.registerCleansingCenter(comParams.get(0));
                break;
            case "RegisterAdoptionCenter":
                this.manager.registerAdoptionCenter(comParams.get(0));
                break;
            case "RegisterCastrationCenter":
                this.manager.registerCastrationCenter(comParams.get(0));
                break;
            case "RegisterDog":
                this.manager.registerDog(
                        comParams.get(0),
                        Integer.parseInt(comParams.get(1)),
                        Integer.parseInt(comParams.get(2)),
                        comParams.get(3));
                break;
            case "RegisterCat":
                this.manager.registerCat(
                        comParams.get(0),
                        Integer.parseInt(comParams.get(1)),
                        Integer.parseInt(comParams.get(2)),
                        comParams.get(3));
                break;
            case "SendForCleansing":
                this.manager.sendForCleansing(
                        comParams.get(0),
                        comParams.get(1));
                break;
            case "SendForCastration":
                this.manager.sendForCastration(
                        comParams.get(0),
                        comParams.get(1));
                break;
            case "Cleanse":
                this.manager.cleanse(comParams.get(0));
                break;
            case "Castrate":
                this.manager.castrate(comParams.get(0));
                break;
            case "Adopt":
                this.manager.adopt(comParams.get(0));
                break;
            case "CastrationStatistics":
                this.manager.castrationStatistics();
                break;
        }
        return null;
    }
}
