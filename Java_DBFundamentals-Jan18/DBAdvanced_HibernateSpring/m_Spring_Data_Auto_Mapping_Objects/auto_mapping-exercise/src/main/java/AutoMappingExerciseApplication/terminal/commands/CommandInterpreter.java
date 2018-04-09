package AutoMappingExerciseApplication.terminal.commands;

import AutoMappingExerciseApplication.controllers.GameController;
import AutoMappingExerciseApplication.controllers.OrderController;
import AutoMappingExerciseApplication.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

@Component
public class CommandInterpreter {

    private final UserController userController;
    private final OrderController orderController;
    private final GameController gameController;
    private BufferedReader reader;

    @Autowired
    public CommandInterpreter(UserController userController, OrderController orderController, GameController gameController) {
        this.userController = userController;
        this.orderController = orderController;
        this.gameController = gameController;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String executeCommands() throws IOException {
        String line ;
        StringBuilder builder = new StringBuilder();
        while(true){
            if("quit".equalsIgnoreCase(line = reader.readLine())){
                break;
            }
            String result = this.executeCommand(line);
            //System.out.println(result);
            builder.append(result).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    public String executeCommand(String line){
        Deque<String> args = new ArrayDeque<>(Arrays.asList(line.split("\\|")));
        //List<String> commandAndArgs = Arrays.asList(line.split("\\|"));
        String command = args.pop();

        switch (command){
            case "RegisterUser" : return this.userController.registerNewUser(args);
            case "LoginUser": return this.userController.login(args);
            case "LogoutUser":
            case "Logout": return this.userController.logOut();
            case "AddGame": return this.gameController.addNewGame(args);
            case "EditGame": return this.gameController.editGame(args);
            case "DeleteGame": return this.gameController.deleteGame(args);
            case "AllGame": return this.gameController.listAllGames();
            case "DetailGame": return this.gameController.detailGame(args);
            case "OwnedGame": return this.userController.getOwnedGames();
            case "AddItem": return this.gameController.addItemToShoppingCard(args);
            case "RemoveItem": return this.gameController.removeItemDromShoppingCard(args);
            case "BuyItem": return this.orderController.buyItem();
            default: return "Unknown command";
        }
    }
}
