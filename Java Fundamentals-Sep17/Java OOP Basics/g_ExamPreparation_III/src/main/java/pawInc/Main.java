package pawInc;

import pawInc.entities.AnimalCenterManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        AnimalCenterManager center = new AnimalCenterManager();
        String command = reader.readLine();

        while (!command.equals("Paw Paw Pawah")) {

            List<String> comParams = new ArrayList<>(Arrays.asList(command.split(" \\| ")));
            String com = comParams.remove(0);
            switch (com) {
                case "RegisterCleansingCenter":
                    center.registerCleansingCenter(comParams.get(0));
                    break;
                case "RegisterAdoptionCenter":
                    center.registerAdoptionCenter(comParams.get(0));
                    break;
                case "RegisterCastrationCenter":
                    center.registerCastrationCenter(comParams.get(0));
                    break;
                case "RegisterDog":
                    center.registerDog(
                            comParams.get(0),
                            Integer.parseInt(comParams.get(1)),
                            Integer.parseInt(comParams.get(2)),
                            comParams.get(3));
                    break;
                case "RegisterCat":
                    center.registerCat(
                            comParams.get(0),
                            Integer.parseInt(comParams.get(1)),
                            Integer.parseInt(comParams.get(2)),
                            comParams.get(3));
                    break;
                case "SendForCleansing":
                    center.sendForCleansing(
                            comParams.get(0),
                            comParams.get(1));
                    break;
                case "SendForCastration":
                    center.sendForCastration(
                            comParams.get(0),
                            comParams.get(1));
                    break;
                case "Cleanse":
                    center.cleanse(comParams.get(0));
                    break;
                case "Castrate":
                    center.castrate(comParams.get(0));
                    break;
                case "Adopt":
                    center.adopt(comParams.get(0));
                    break;
                case "CastrationStatistics":
                    center.castrationStatistics();
                    break;
            }
            command = reader.readLine();
        }
        center.printStatistics();
    }
}
