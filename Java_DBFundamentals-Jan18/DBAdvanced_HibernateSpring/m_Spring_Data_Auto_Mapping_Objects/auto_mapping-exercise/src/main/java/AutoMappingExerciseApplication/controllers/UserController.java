package AutoMappingExerciseApplication.controllers;

import AutoMappingExerciseApplication.dto.LoginDto;
import AutoMappingExerciseApplication.dto.RegisterUserDto;
import AutoMappingExerciseApplication.models.entities.Game;
import AutoMappingExerciseApplication.models.entities.User;
import AutoMappingExerciseApplication.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public String registerNewUser(Deque<String> args) {
        String email = args.pop();
        String password = args.pop();
        String passwordConfirmation = args.pop();
        String fullName = args.pop();
        RegisterUserDto userDto = new RegisterUserDto(
                email,
                password,
                passwordConfirmation,
                fullName
        );
        return this.userService.registerUser(userDto);
    }

    public String login(Deque<String> args){
        String email = args.pop();
        String password = args.pop();
        LoginDto loginDto = new LoginDto(email, password);
        User user = this.userService.login(loginDto);
        if (user == null){
            return "Incorrect username / password";
        } else {
            userSession.login(user);
            return String.format("Successfully logged as %s", user.getFullName());
        }
    }

    public String logOut(){
        if(userSession.isCurrentUserLoggedIn()){
            String fullName = userSession.getCurrentUser().getFullName();
            userSession.logout();
            return String.format("User %s successfully logged out", fullName);
        }
        return "Cannot log out. No user was logged in.";
    }

    public String getOwnedGames(){
        if(!userSession.isCurrentUserLoggedIn()){
            return "Please log in";
        }
        User user = userSession.getCurrentUser();
        List<String> gamesNames = this.userService.getUserWithGames(user.getId()).getGames()
                .stream()
                .map(Game::getTitle)
                .collect(Collectors.toList());
        return String.join(System.lineSeparator(), gamesNames);
    }
}
