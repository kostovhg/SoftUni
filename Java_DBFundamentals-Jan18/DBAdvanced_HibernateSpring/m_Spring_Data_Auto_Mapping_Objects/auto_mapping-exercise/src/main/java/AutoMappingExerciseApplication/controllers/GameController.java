package AutoMappingExerciseApplication.controllers;

import AutoMappingExerciseApplication.dto.GameDto;
import AutoMappingExerciseApplication.models.entities.Game;
import AutoMappingExerciseApplication.models.entities.User;
import AutoMappingExerciseApplication.services.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GameController extends BaseController {

    private final GameService gameService;
    private final DateFormat format;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
        this.format = new SimpleDateFormat("dd-MM-yyyy");
    }
    
    public String addNewGame(Deque<String> args){
        if(!userSession.isCurrentUserAdmin()){
            return "You don't have permission to add game";
        }
        if( args.size() < 7){
            return "You should provide all game parameters in format:\n " + "AddGame|<title>|<price>|<size>|<thubnailURL>|<description>|<releaseDate>";
        }
        String title = args.pop();
        BigDecimal price = new BigDecimal(args.pop());
        BigDecimal size = new BigDecimal(args.pop());
        String trailer = args.pop();
        String thumbnailURL = args.pop();
        String description = args.pop();
        Date releaseDate = null;
        try {
            releaseDate = this.format.parse(args.pop());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GameDto gameDto = new GameDto(title, price, size, trailer, thumbnailURL, description, releaseDate);
        
        return this.gameService.addGame(gameDto);
    }
    
    public String editGame(Deque<String> args){
        if(!userSession.isCurrentUserAdmin()){
            return "You don't have permission to edit games";
        }
        Long id = Long.valueOf(args.pop());

        Map<String, String> fieldValueMap = new HashMap<>();
        while(args.peek() != null){
            String[] kvp = args.pop().split("\\s*=\\s*");
            fieldValueMap.put(kvp[0], kvp[1]);
        }

        GameDto gameDto = null;
        try{
            gameDto = new GameDto(fieldValueMap);
        } catch (Exception e){
            return "Can not edit game. Incorrect input parameters.";
        }

        return this.gameService.editGame(gameDto, id);
    }

    public String deleteGame(Deque<String> args){
        if(!userSession.isCurrentUserAdmin()){
            return "You don't have permission to delete games";
        }
        if( args.peek() ==  null){
            return "You should provide id of the game.";
        }
        Long id = Long.valueOf(args.pop());

        return this.gameService.deleteGame(id);
    }

    public String listAllGames() {
        return String.join(System.lineSeparator(), this.gameService.getAllGames().collect(Collectors.toList()));
    }

    public String detailGame(Deque<String> args) {
        String title;
        if(args.size() < 1){
            return "You should provide title.";
        }
        title = args.pop();
        return this.gameService.gameDetails(title);
    }


    public String addItemToShoppingCard(Deque<String> args){
        if(!userSession.isCurrentUserLoggedIn()){
            return "Please log in";
        }
        String title = args.pop();
        if(title == null){
            return "Please choose title";
        }
        Game game = this.gameService.gameByTitle(title);
        if(game == null){
            return "There is no game with title " + title;
        }

        if(userSession.getCurrentUser().getGames().contains(game)){
            return String.format("%s already exists in your games.", game.getTitle());
        }
        if(userSession.addItemToShoppingCard(game)){
            return String.format("%s added to card.", game.getTitle());
        } else {
            return String.format("%s already exists in card.", game.getTitle());
        }
    }

    public String removeItemDromShoppingCard(Deque<String> args){
        if(!userSession.isCurrentUserLoggedIn()){
            return "Please log in";
        }

        User user = userSession.getCurrentUser();
        String title = args.pop();
        Game game = this.gameService.gameByTitle(title);
        if(game == null){
            return "Game doesn't exist";
        }

        if(userSession.removeItemFromShoppingCard(game)){
            return String.format("%s removed from card.", title);
        } else {
            return String.format("%s doesn't exits in card.", title);
        }
    }
}
